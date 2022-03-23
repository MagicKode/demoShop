package com.example.demoshop.service;

import com.example.demoshop.model.Product;
import com.example.demoshop.model.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

public interface ProductServiceInterface {

    List<Product> getProductsByTitle(String title);

    void saveProduct(MultipartFile file1, MultipartFile file2, MultipartFile file3,
                     MultipartFile file4,Principal principal,  Product product) throws IOException;

    void deleteProductById(Long id);

    Product getProductById(Long id);

    User getUserByPrincipal(Principal principal);

}
