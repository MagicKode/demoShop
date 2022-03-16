package com.example.demoshop.repository;

import com.example.demoshop.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByTitle(String title); //как пример метода получения продукта по id
}
