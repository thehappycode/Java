package com.thehappycode.product.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.thehappycode.product.dto.CreateProductRequestDto;
import com.thehappycode.product.dto.CreateProductResponseDto;
import com.thehappycode.product.dto.ProductResponseDto;
import com.thehappycode.product.service.ProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    /**
     * /api/product/createProduct
     * Api tạo một product
     * @param productRequest
    */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateProductResponseDto createProduct(@RequestBody CreateProductRequestDto createProductRequestDto){

        return productService
            .createProduct(createProductRequestDto); 
    }

    /**
     * /api/product/getAllProducts
     * Api lấy tất cả Product
     * @return
    */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponseDto> getListProducts(){
        return productService
            .getListProducts();
    }
} 
