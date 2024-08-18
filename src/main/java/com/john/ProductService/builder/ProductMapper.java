package com.john.ProductService.builder;

import com.john.ProductService.dto.FakeStoreProductDTO;
import com.john.ProductService.dto.ProductResponseDTO;
import com.john.ProductService.model.Category;
import com.john.ProductService.model.Product;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper {
    public ProductResponseDTO convertToProductResponseDTO(Product product) {
        ProductResponseDTO dto = new ProductResponseDTO();
        dto.setId(product.getId());
        dto.setTitle(product.getTitle());
        dto.setPrice(product.getPrice());
        dto.setDescription(product.getDescription());
        dto.setCategory(product.getCategory());
        dto.setImageURL(product.getImageURL());
        return dto;
    }
    public Product mapToProduct(FakeStoreProductDTO dto){
        Product product = new Product();
        product.setId(dto.getId());
        product.setTitle(dto.getTitle());
        product.setDescription(dto.getDescription());
        product.setPrice(Double.valueOf(dto.getPrice()));
        product.setImageURL(dto.getImage());

        Category category = new Category();
        category.setTitle(dto.getCategory());
        product.setCategory(category);

        return product;
    }
}
