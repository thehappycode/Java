package com.thehappycode.product.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.thehappycode.product.model.Product;

public interface ProductRepository extends MongoRepository<Product, String>{

}


