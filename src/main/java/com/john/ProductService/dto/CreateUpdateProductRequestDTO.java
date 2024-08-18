package com.john.ProductService.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUpdateProductRequestDTO {
    private String title;
    private String description;
    private String price;
    private String image;
    private String category;
}

