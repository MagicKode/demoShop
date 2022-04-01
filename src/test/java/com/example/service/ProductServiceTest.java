package com.example.service;

import com.example.demoshop.model.Image;
import com.example.demoshop.model.Product;
import com.example.demoshop.model.User;
import com.example.demoshop.repository.ProductRepository;
import com.example.demoshop.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    Product product;
    User user;
    Image image;


    @BeforeEach
    void init() {

        product = new Product();
        user = new User();
        image = new Image();


        //products
        product.setId(1L);
        product.setTitle("Auto");
        product.setDescription("fast");
        product.setManufacturer("Dodge");
        product.setPrice(123456);
        product.setCity("USA");

        //user
        user.setName("A");
        user.setLogin("B");
        user.setPhoneNumber("123456");
        user.setEmail("asd@ads");
        user.setActive(true);

        //image
        image.setImgName("name1");
        image.setOriginalFileName("fileName1");
        image.setContentType("content1");
        image.setContentType("content4");
        image.setPreviewImage(true);

    }


    @Test
    void shouldGetProductsByTitle() {
        //given
        String title = "Auto";
        when(productRepository.findByTitle(title)).thenReturn(null);

        //when
        productService.getProductsByTitle(title);

        //then
        assertEquals("Auto", title);

        verify(productRepository, times(1)).findByTitle(title);
    }

    @Test
    void shouldSetImageToProduct() {
        //given
        Image image1 = new Image();
        List<Image> images = new ArrayList<>();

        // MultipartFile file1 = new MockMultipartFile() //создать , инициализировать !!!
        MultipartFile file2;
        MultipartFile file3;
        MultipartFile file4;

        /*Image image = new Image();
        image.setImgName("name1");
        image.setOriginalFileName("fileName1");
        image.setContentType("content1");
        image.setPreviewImage(true);*/


        image1.setPreviewImage(true);
        product.addImageToProduct(image1);


        //when


        //then
   //     assertNotNull(file1);
      //  assertEquals();
    }

    @Test
    void shouldSaveProduct() throws IOException { //разобраться с фотографиями
        //given
        when(productRepository.save(product)).thenReturn(this.product);

        //when
        productService.saveProduct(null, null, null,
                null, null, product);

        //then
        assertNotNull(this.product);

        verify(productRepository, times(1)).save(product);

    }

    @Test
    void shouldDeleteProduct() {
        //given
        Long id = 1L;
        //when(productRepository.deleteById(1L)).thenReturn(Optional.of(product)); //как замокать void как сделать verify с void!
        //when
        productService.deleteProductById(id);

        //then
        verify(productRepository, times(1)).deleteById(id);

    }

    @Test
    void shouldGetUserByPrincipal() {
        //given

        //when


        //then

    }

    @Test
    void shouldGetProductById() {
        //given
        Long id = 1L;
        when(productRepository.getById(1L)).thenReturn(null);

        //when
        productService.getProductById(id);

        //then
        verify(productRepository, Mockito.times(1)).getById(id);
    }
}