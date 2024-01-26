package org.arghya.app.productservice.controller;

import org.arghya.app.productservice.dto.ProductRequest;
import org.arghya.app.productservice.dto.ProductResponse;
import org.arghya.app.productservice.model.Product;
import org.arghya.app.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/online-shopping")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @PostMapping("/product")
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody ProductRequest productRequest){
        productService.createProduct(productRequest);
    }

    @GetMapping("/products")
    public HttpEntity<List<ProductResponse>> getProducts(){
        return new ResponseEntity<>(productService.getProducts(), HttpStatus.OK);
    }
}
