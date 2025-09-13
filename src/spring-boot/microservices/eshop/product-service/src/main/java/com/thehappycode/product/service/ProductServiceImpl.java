package com.thehappycode.product.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.thehappycode.product.dto.CreateProductRequestDto;
import com.thehappycode.product.dto.CreateProductResponseDto;
import com.thehappycode.product.dto.ProductResponseDto;
import com.thehappycode.product.model.Product;
import com.thehappycode.product.repository.ProductRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service 
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {
   
    private final ProductRepository productRepository;

    @Override
    public CreateProductResponseDto createProduct(CreateProductRequestDto createProductRequestDto) {
        Product product = Product.builder()
            .name(createProductRequestDto.name())
            .description(createProductRequestDto.description())
            .price(createProductRequestDto.price())
            .build();
        productRepository.save(product);

        log.info("Product created successfully");
            
        return new CreateProductResponseDto(
            product.getId(),
            product.getName(),
            product.getDescription(),
            product.getPrice()
        );
    }

    @Override
    public List<ProductResponseDto> getListProducts() {
        List<ProductResponseDto> products =  productRepository
            .findAll()
            .stream()
            .map(product -> new ProductResponseDto(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice()
            ))
            .toList();

        log.info("Get all prooducts successfully");

        return products;
    }

}


