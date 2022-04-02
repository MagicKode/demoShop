package com.example.service;

import com.example.demoshop.model.Image;
import com.example.demoshop.model.Product;
import com.example.demoshop.model.User;
import com.example.demoshop.repository.ProductRepository;
import com.example.demoshop.repository.UserRepository;
import com.example.demoshop.service.ProductService;
import com.example.demoshop.service.UserService;
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
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;
    private UserRepository userRepository;

    @InjectMocks
    private ProductService productService;
    private UserService userService;

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
        user.setName("name");
        user.setLogin("login");
        user.setPhoneNumber("123456");
        user.setEmail("asd@ads");
        user.setActive(true);

        //image
        image.setImgName("image");
        image.setOriginalFileName("file");
        image.setContentType("content");
        image.setPreviewImage(true);

        //image(multiPartFiles)
        MultipartFile file1 = new MockMultipartFile(
                "name1",
                "file1.txt",
                MediaType.TEXT_PLAIN_VALUE,
                "Hello, World!".getBytes()); //создать , инициализировать !!!
        MultipartFile file2 = new MockMultipartFile(
                "name2",
                "file2.txt",
                MediaType.TEXT_PLAIN_VALUE,
                "Hello, World!".getBytes());
        MultipartFile file3 = new MockMultipartFile(
                "name3",
                "file3.txt",
                MediaType.TEXT_PLAIN_VALUE,
                "Hello, World!".getBytes());
        MultipartFile file4 = new MockMultipartFile(
                "name4",
                "file4.txt",
                MediaType.TEXT_PLAIN_VALUE,
                "Hello, World!".getBytes());
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
        List<Image> images = new ArrayList<>();

        Image image1 = new Image();
        Image image2 = new Image();
        Image image3 = new Image();
        Image image4 = new Image();


        images.add(image1);
        images.add(image2);
        images.add(image3);
        images.add(image4);

        //when



        //then
        assertNotNull(file1);
        assertNotNull(file2);
        assertNotNull(file3);
        assertNotNull(file4);
        //assertEquals();
    }

    @Test
    void shouldSaveProduct() throws IOException { //разобраться с фотографиями
        MockMultipartFile file1 = new MockMultipartFile();
        MockMultipartFile file2 = new MockMultipartFile();
        MockMultipartFile file3 = new MockMultipartFile();
        MockMultipartFile file4 = new MockMultipartFile();
        //given

        when(productRepository.save(product)).thenReturn(this.product);

        //when
        productService.saveProduct(file1, file2, file3, file4, product);

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
        String login = "login";
        Principal principal = new MockPrincipal();
        when(userRepository.findByLogin(login)).thenReturn(this.user);

        //when
        productService.getUserByPrincipal(principal);

        //then
        assertNotNull(this.user);
        assertEquals("login", login);

        verify(userRepository, times(1)).findByLogin(login);
        verify(productService, times(1)).getUserByPrincipal(principal);

    }

    @Test
    void shouldGetProductById() {
        //given
        Product product = new Product();
        product.setId(1L);

        //when
        productService.getProductById(product.getId());

        //then
        assertEquals(1L, product.getId());

        verify(productRepository, Mockito.times(1)).findById(product.getId());
    }
}