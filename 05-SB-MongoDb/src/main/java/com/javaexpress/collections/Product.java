package com.javaexpress.collections;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@Document(collection = "product")
public class Product {

    @Id
    private String id;

    private String name;

    private double price;
}
