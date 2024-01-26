package org.arghya.app.orderservice.service;

import lombok.RequiredArgsConstructor;
import org.arghya.app.orderservice.dto.InventoryResponse;
import org.arghya.app.orderservice.dto.OrderLineItemsDto;
import org.arghya.app.orderservice.dto.OrderRequest;
import org.arghya.app.orderservice.event.OrderPlacedEvent;
import org.arghya.app.orderservice.model.Order;
import org.arghya.app.orderservice.model.OrderLineItems;
import org.arghya.app.orderservice.repository.OrderRepository;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    private final WebClient.Builder webClientBuilder;
    private final KafkaTemplate<String, OrderPlacedEvent> kafkaTemplate;

    public String placeOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        List<OrderLineItems> orderLineItemsList = orderRequest.getOrderLineItemsDtoList().stream()
                .map(this::maptoOrderLineItem)
                .collect(Collectors.toList());
        order.setOrderLineItems(orderLineItemsList);

        List<String> skuCodes = order.getOrderLineItems().stream()
                .map(OrderLineItems::getSkuCode)
                .collect(Collectors.toList());
        InventoryResponse[]  inventoryResponses = webClientBuilder.build().get()
                .uri("http://inventory-service/online-shopping/inventory/stock",
                        uriBuilder -> uriBuilder.queryParam("skuCodes", skuCodes).build())
                .retrieve()
                .bodyToMono(InventoryResponse[].class)
                .block();
        boolean isInStock = Arrays.stream(inventoryResponses).allMatch(InventoryResponse::isInStock);
        if(isInStock){
            orderRepository.save(order);
            kafkaTemplate.send("noificationTopic", new OrderPlacedEvent(order.getOrderNumber()));
            return "order placed successfully";
        } else {
            throw new IllegalArgumentException("product is out of stock");
        }
    }

    private OrderLineItems maptoOrderLineItem(OrderLineItemsDto orderLineItemsDto) {
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        return orderLineItems;
    }
}
