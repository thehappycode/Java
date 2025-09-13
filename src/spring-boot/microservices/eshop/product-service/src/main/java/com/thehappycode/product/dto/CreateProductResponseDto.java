package com.thehappycode.product.dto;

import java.math.BigDecimal;

public record CreateProductResponseDto(
    String id,
    String name,
    String description,
    BigDecimal price
){

}

