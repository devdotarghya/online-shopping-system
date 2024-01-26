package org.arghya.app.productservice.service;

import org.arghya.app.productservice.dto.ProductRequest;
import org.arghya.app.productservice.dto.ProductResponse;
import org.arghya.app.productservice.model.Product;
import org.arghya.app.productservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    public void createProduct(ProductRequest productRequest) {
        Product product = Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();
        productRepository.save(product);
    }

    public List<ProductResponse> getProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(this::mapToProductReponse)
                .collect(Collectors.toList());
    }

    private ProductResponse mapToProductReponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }
}

