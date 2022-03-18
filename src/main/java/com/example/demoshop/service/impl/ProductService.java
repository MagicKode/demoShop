package com.example.demoshop.service.impl;

import com.example.demoshop.model.Image;
import com.example.demoshop.model.Product;
import com.example.demoshop.model.User;
import com.example.demoshop.repository.ProductRepository;
import com.example.demoshop.repository.UserRepository;
import com.example.demoshop.service.ProductServiceInterface;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService implements ProductServiceInterface {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;

   /* private List<Product> products = new ArrayList<>();
    private long ID = 0;

    {
        products.add(new Product(++ID, "PlayStation 5", "Simple description", 67000, "Krasnoyarsk", "tomas"));
        products.add(new Product(++ID, "Iphone 8", "Simple description", 24000, "Moscow", "dima"));
    }*/

    @Override
    public List<Product> getProductsByTitle(String title) { //достайм все продукты ПО НАЗВАНИЮ изи резитория
        if (title != null) return productRepository.findByTitle(title);
        return productRepository.findAll();
    }

    @Override
    public void saveProduct(Principal principal, MultipartFile file1, MultipartFile file2, MultipartFile file3,
                            MultipartFile file4, Product product) throws IOException {
        product.setUser(getUserByPrincipal(principal)); // это Обёртка , в каком состоянии находится Юзер
        Image image1;
        Image image2;
        Image image3;
        Image image4;
        if (file1.getSize() != 0){
            image1 = toImageEntity(file1);
            image1.setPreviewImage(true);
            product.addImageToProduct(image1);
        }
        if (file2.getSize() != 0){
            image2 = toImageEntity(file2);
            product.addImageToProduct(image2);
        }
        if (file3.getSize() != 0){
            image3 = toImageEntity(file3);
            product.addImageToProduct(image3);
        }
        if (file4.getSize() != 0){
            image4 = toImageEntity(file4);
            product.addImageToProduct(image4);
        }

        log.info("Saving new Product. Title: {}; Author login: {}", product.getTitle(), product.getUser().getLogin()/*getAuthor()*/);
        Product productFromDB = productRepository.save(product); //присваиваем фотографию конкретному продукту
        productFromDB.setPreviewImageId(productFromDB.getImages().get(0).getId()); //получаем по 0му инлексу Первое фото, устанавливаем ему индекс 1 для сохранения в БД
        productRepository.save(product); // обновляем репозиторий,(добавляем продукт уже С ФОТО)

        /*product.setId(++ID);
        products.add(product);*/
    }

    public User getUserByPrincipal(Principal principal) {
        if(principal == null) return new User();  // ноый Юзер, т.к. новому Юзеру НЕ БУДЕТ показана формочка Удаления товара
        return userRepository.findByLogin(principal.getName());
    }

    private Image toImageEntity(MultipartFile file) throws IOException {
        Image image = new Image();
        image.setImgName(file.getName());
        image.setOriginalFileName(file.getOriginalFilename());
        image.setSize(file.getSize());
        image.setContentType(file.getContentType());
        image.setBytes(file.getBytes());
        return image;
    }

    @Override
    public void deleteProduct(Long id) {  //удаление
        /*products.removeIf(product -> product.getId().equals(id));*/
        productRepository.deleteById(id);
    }

    @Override
    public Product getProductById(Long id) { //достаём продукт по id
       /* for (Product product : products) {
            if (product.getId().equals(id)) return product;
        }
        return null;*/

        return productRepository.findById(id).orElse(null); //если товара с таким id  не найдено,ю то вернёт NULL
    }
}
