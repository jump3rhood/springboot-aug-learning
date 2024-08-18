package com.john.ProductService.dto;

import com.john.ProductService.model.Category;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductResponseDTO {
    private Integer id;
    private String title;
    private String description;
    private Double price;
    private String imageURL;
    private Category category;
}
