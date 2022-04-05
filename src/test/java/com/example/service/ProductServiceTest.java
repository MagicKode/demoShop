package com.example.service;

import com.example.demoshop.model.Image;
import com.example.demoshop.model.Product;
import com.example.demoshop.model.User;
import com.example.demoshop.repository.ProductRepository;
import com.example.demoshop.repository.UserRepository;
import com.example.demoshop.service.ProductService;
import org.apache.struts.mock.MockPrincipal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.core.parameters.P;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private ProductService testSubject;


    Product product;
    User user;
    Image image;
    MockMultipartFile file1;
    Principal principal;


    @BeforeEach
    void init() {

        product = new Product();
        user = new User();
        image = new Image();
        principal = new MockPrincipal("name");


        //products
        product.setId(1L);
        product.setTitle("Auto");
        product.setDescription("fast");
        product.setManufacturer("Dodge");
        product.setPrice(123456);
        product.setCity("USA");

        //user
        user.setName("name");
        user.setLogin("login");
        user.setPhoneNumber("123456");
        user.setEmail("asd@ads");
        user.setActive(true);

        //image
        image = new Image();
        image.setImgName("image");
        image.setOriginalFileName("file");
        image.setContentType("content");

        //image(multiPartFiles)
        file1 = new MockMultipartFile(
                "name1",
                "file1.png",
                MediaType.IMAGE_PNG_VALUE,
                "Hello, World!".getBytes());
    }


    @Test
    void shouldGetProductsByTitle() {
        //given
        Product product = new Product();
        String title = "Auto";
        product.setTitle(title);
        List<Product> getProductsByTitle = new ArrayList<>();
        getProductsByTitle.add(product);

        when(productRepository.findByTitle(product.getTitle())).thenReturn(getProductsByTitle);

        //when
        testSubject.getProductsByTitle(title);

        //then
        assertEquals("Auto", title);

        verify(productRepository, times(1)).findByTitle(title);
    }


    @Test
    void shouldSaveProduct() throws IOException {
        //given
        Product product = new Product();
        MockMultipartFile testFile = new MockMultipartFile( //переименовать
                "file1", "file1.txt",
                MediaType.TEXT_PLAIN_VALUE,
                "Hello, World!".getBytes());
        when(userRepository.findByLogin(principal.getName())).thenReturn(user);
        when(productRepository.save(product)).thenReturn(product);

        //when
        testSubject.saveProduct(testFile, null, null, null, principal, product);

        //then
        verify(userRepository, times(1)).findByLogin(principal.getName());
        verify(productRepository, times(2)).save(product);
    }


    @Test
    void shouldDeleteProductById() {
        //given
        Product product = new Product();
        Long id = 1L;
        product.setId(id);
        productRepository.deleteById(id);

        //when
        testSubject.deleteProductById(id);

        //then

        verify(productRepository, times(2)).deleteById(id);
    }


    @Test
    void shouldGetProductById() {
        //given
        Product product = new Product();
        Long id = 1L;
        product.setId(id);
        when(productRepository.getById(id)).thenReturn(product);

        //when
        testSubject.getProductById(product.getId());

        //then
        assertEquals(1L, product.getId());

        verify(productRepository, Mockito.times(1)).findById(product.getId());
    }


    @Test
    void shouldGetUserByPrincipal() {
        //given
        User user = new User();
        String login = "login";
        user.setLogin(login);
        Principal principal = new MockPrincipal();
        /*String name = "login";
        principal.(name);*/

        when(userRepository.findByLogin(login)).thenReturn(user);


        //when
        testSubject.getUserByPrincipal(principal);

        //then
        assertNotNull(this.user);
        assertEquals("login", login);

        verify(userRepository, times(1)).findByLogin(login);
        // verify(testSubject, times(1)).getUserByPrincipal(principal);

    }
}