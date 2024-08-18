package com.john.ProductService.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Product {
    private Integer id;
    private String title;
    private String description;
    private Double price;
    private String imageURL;
    private Category category;
}
