package com.javaexpress.repo;

import com.javaexpress.collections.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
}
