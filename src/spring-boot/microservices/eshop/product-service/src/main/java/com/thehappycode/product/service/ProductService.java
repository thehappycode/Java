package com.thehappycode.product.service;

import java.util.List;

import com.thehappycode.product.dto.CreateProductRequestDto;
import com.thehappycode.product.dto.CreateProductResponseDto;
import com.thehappycode.product.dto.ProductResponseDto;
import com.thehappycode.product.model.Product;

/** 
 * Interface xử lý nghiệp vụ liên quan đến Product
 * */
public interface ProductService {

    /**
     * Tạo một Product
     * @param createProductDto
    */
    CreateProductResponseDto createProduct(CreateProductRequestDto createProductRequestDtoDto);


    /**
     * Lấy tất cả danh sách Proudct
     * @return
    */
    List<ProductResponseDto> getListProducts();
}


