package com.john.ProductService.controller;

import com.john.ProductService.builder.ProductMapper;
import com.john.ProductService.dto.CreateUpdateProductRequestDTO;
import com.john.ProductService.dto.FakeStoreProductDTO;
import com.john.ProductService.dto.ProductResponseDTO;
import com.john.ProductService.exception.InvalidProductIdException;
import com.john.ProductService.exception.ProductNotFoundException;
import com.john.ProductService.model.Product;
import com.john.ProductService.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;



@RestController
public class ProductController {
    ProductService service;
    ProductMapper mapper;

    public ProductController(ProductService service, ProductMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping("/products")
    public List<ProductResponseDTO> getAllProducts() {
        List<Product> products = service.getAllProducts();
        List<ProductResponseDTO> responseDTOS = new ArrayList<>();
        for (Product product : products) {
            responseDTOS.add(mapper.convertToProductResponseDTO(product));
        }
        return responseDTOS;
    }

    @GetMapping("/product/{id}")
    public ProductResponseDTO getProductById(@PathVariable Integer id) throws InvalidProductIdException, ProductNotFoundException {
        if(id == null){
            System.out.println("id cannot be null");
            throw new InvalidProductIdException("Product id is not correct or null");
        }
        System.out.println("Search for product with id " + id);
        Product product = service.getProductById(id);
        if(product == null){
            throw new ProductNotFoundException("Product with id is not found or null");
        }
        return mapper.convertToProductResponseDTO(product);
    }

    @PostMapping("/products")
    public ProductResponseDTO createProduct(@RequestBody CreateUpdateProductRequestDTO dto) throws Exception{
        // S1. Validation. Send 400 Bad request if request body is incorrect
        if(dto.getTitle() == null){
            System.out.println("Title of the product is null");
            throw new Exception("Product title is required");
        }
        if(dto.getPrice() == null){
            System.out.println("Price of the product is null");
            throw new Exception("Product price is required");

        }
        if(dto.getDescription() == null){
            System.out.println("Description of the product is null");
            throw new Exception("Product description is required");
        }
        if(dto.getCategory() == null){
            System.out.println("Category of the product is null");
            throw new Exception("Product category is required");
        }
        if(dto.getImage() == null){
            System.out.println("Image of the product is null");
            throw new Exception("Product image is required");
        }
        // S2. create product on fakestore
        Product product = service.createProduct(dto.getTitle(), dto.getDescription(), dto.getPrice(), dto.getImage(), dto.getCategory());
        // S3. Get back the product and send ResponseDTO to client with 201 Created status code
        ProductResponseDTO responseDTO = mapper.convertToProductResponseDTO(product);
        return responseDTO;
    }

    @PutMapping("/product/{id}")
    public ProductResponseDTO updateProductById(@PathVariable Integer id, @RequestBody CreateUpdateProductRequestDTO dto) throws Exception{
        if(dto.getPrice() == null){
            System.out.println("Price of the product is null");
            throw new Exception("Product price is required");

        }
        if(dto.getDescription() == null){
            System.out.println("Description of the product is null");
            throw new Exception("Product description is required");
        }
        if(dto.getCategory() == null){
            System.out.println("Category of the product is null");
            throw new Exception("Product category is required");
        }
        if(dto.getImage() == null){
            System.out.println("Image of the product is null");
            throw new Exception("Product image is required");
        }
        Product updatedProduct = service.updateProduct(id, dto.getTitle(), dto.getDescription(), dto.getPrice(), dto.getImage(), dto.getCategory());
        return mapper.convertToProductResponseDTO(updatedProduct);
    }

    @DeleteMapping("/product/{id}")
    public ProductResponseDTO deleteProductById(@PathVariable Integer id){
        Product deletedProduct =  service.deleteProduct(id);
        return mapper.convertToProductResponseDTO(deletedProduct);
    }



}
