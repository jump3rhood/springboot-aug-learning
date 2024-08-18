package com.john.ProductService.service;

import com.john.ProductService.builder.ProductMapper;
import com.john.ProductService.dto.FakeStoreProductDTO;
import com.john.ProductService.model.Product;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreService implements ProductService{
    private RestTemplate restTemplate;
    private ProductMapper mapper;
    private String baseUrl = "https://fakestoreapi.com";
    public FakeStoreService(RestTemplate restTemplate, ProductMapper mapper) {
        this.restTemplate = restTemplate;
        this.mapper = mapper;
    }

    @Override
    public Product getProductById(int id) {
        // validate id
        // 1. Call the API
       ResponseEntity<FakeStoreProductDTO> res = restTemplate
               .getForEntity(baseUrl + "/products/" + id, FakeStoreProductDTO.class );

       if(res == null || res.getBody() == null){
           // throw exception
           System.out.println("Product Body is null");
           return null;
       }
       // 2. Get the body
       FakeStoreProductDTO dto = res.getBody();

        // 3. Return model from the method
       return mapper.mapToProduct(dto);
    }

    @Override
    public List<Product> getAllProducts() {
        ResponseEntity<FakeStoreProductDTO[]> response = restTemplate
                .getForEntity(baseUrl + "/products", FakeStoreProductDTO[].class);
        FakeStoreProductDTO[] dtoArray = response.getBody();
        if(dtoArray == null || dtoArray.length == 0){
            System.out.println("Product List is null");
            return null;
        }
        List<Product> products = new ArrayList<>();

        for(FakeStoreProductDTO dto : dtoArray){
            products.add(mapper.mapToProduct(dto));
        }
        return products;
    }
    @Override
    public Product createProduct(String title, String description, String price, String image, String category ) {

        // convert the args to request body to be sent => FakeStore
        FakeStoreProductDTO requestBody = new FakeStoreProductDTO();
        requestBody.setTitle(title);
        requestBody.setDescription(description);
        requestBody.setPrice(price);
        requestBody.setImage(image);
        requestBody.setCategory(category);

        ResponseEntity<FakeStoreProductDTO> response = restTemplate
                .postForEntity(baseUrl + "/products", requestBody, FakeStoreProductDTO.class );
        Product product = mapper.mapToProduct(response.getBody());
        return product;
    }
    @Override
    public Product updateProduct(Integer id, String title, String description, String price, String image, String category){
        FakeStoreProductDTO requestBody = new FakeStoreProductDTO();
        requestBody.setTitle(title);
        requestBody.setDescription(description);
        requestBody.setPrice(price);
        requestBody.setImage(image);
        requestBody.setCategory(category);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        // Wrap in http entity
        HttpEntity<FakeStoreProductDTO> entity = new HttpEntity<>(requestBody, headers);
        ResponseEntity<FakeStoreProductDTO> response  = restTemplate.exchange(baseUrl + "/products/" + id, HttpMethod.PUT, entity, FakeStoreProductDTO.class);

        FakeStoreProductDTO updatedProduct = response.getBody();
        return mapper.mapToProduct(updatedProduct);
    }
    @Override
    public Product deleteProduct(Integer id) throws HttpClientErrorException {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        HttpEntity<Object> entity = new HttpEntity<>(headers);
        ResponseEntity<FakeStoreProductDTO> response = restTemplate
                .exchange(baseUrl + "/products/" + id, HttpMethod.DELETE, entity, FakeStoreProductDTO.class);
        return mapper.mapToProduct(response.getBody());
    }
}
