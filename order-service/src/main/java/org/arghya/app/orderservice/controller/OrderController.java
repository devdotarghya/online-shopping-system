package org.arghya.app.orderservice.controller;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.RequiredArgsConstructor;
import org.arghya.app.orderservice.dto.OrderRequest;
import org.arghya.app.orderservice.repository.OrderRepository;
import org.arghya.app.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/online-shopping")
@RequiredArgsConstructor
public class OrderController {

    @Autowired
    private final OrderService orderService;

    @PostMapping("/order")
    @CircuitBreaker(name = "inventory", fallbackMethod = "fallbackMethod")
    @TimeLimiter(name = "inventory")
    @Retry(name = "inventory")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public CompletableFuture<String> placeOrderRequest(@RequestBody OrderRequest orderRequest) {
        return CompletableFuture.supplyAsync(()->orderService.placeOrder(orderRequest));
    }

    public CompletableFuture<String> fallbackMethod(OrderRequest orderRequest, RuntimeException exception) {
        return CompletableFuture.supplyAsync(() -> "something went wrong!");
    }
}
