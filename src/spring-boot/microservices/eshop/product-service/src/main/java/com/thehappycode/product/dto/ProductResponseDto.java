package com.thehappycode.product.dto;

import java.math.BigDecimal;

public record ProductResponseDto(
    String id,
    String name,
    String description,
    BigDecimal price
){

}

