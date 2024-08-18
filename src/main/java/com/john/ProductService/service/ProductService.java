package com.john.ProductService.service;

import com.john.ProductService.dto.FakeStoreProductDTO;
import com.john.ProductService.model.Product;

import java.util.List;

public interface ProductService {
    public Product getProductById(int id);
    public List<Product> getAllProducts();
    public Product createProduct(String title, String description, String price, String image, String category);
    public Product updateProduct(Integer id, String title, String description, String price, String image, String category);
    public Product deleteProduct(Integer id);
}
