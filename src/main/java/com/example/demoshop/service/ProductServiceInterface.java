package com.example.demoshop.service;

import com.example.demoshop.model.Product;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProductServiceInterface {

    List<Product> getAllProducts(String title);

    void saveProduct(MultipartFile file1, MultipartFile file2, MultipartFile file3,
                     MultipartFile file4, Product product) throws IOException;

    void deleteProduct(Long id);

    Product getProductById(Long id);
}
