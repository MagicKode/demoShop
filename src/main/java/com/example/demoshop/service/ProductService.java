package com.example.demoshop.service;

import com.example.demoshop.model.Image;
import com.example.demoshop.model.Product;
import com.example.demoshop.model.User;
import com.example.demoshop.repository.ProductRepository;
import com.example.demoshop.repository.UserRepository;
import com.example.demoshop.service.impl.ProductServiceInterface;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService implements ProductServiceInterface {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;


    @Override
    public List<Product> getProductsByTitle(String title) { //достайм все продукты ПО НАЗВАНИЮ изи резитория
        if (title != null) return productRepository.findByTitle(title);
        return productRepository.findAll();
    }


    @Override
    public void saveProduct(MultipartFile file1, MultipartFile file2, MultipartFile file3,
                            MultipartFile file4, Principal principal, Product product) throws IOException {
        product.setUser(getUserByPrincipal(principal)); // это Обёртка , в каком состоянии находится Юзер
        Image image1;
        Image image2;
        Image image3;
        Image image4;
        if (file1.getSize() != 0) {
            image1 = toImageEntity(file1);
            image1.setPreviewImage(true);
            product.addImageToProduct(image1);
        }
        if (file2.getSize() != 0) {
            image2 = toImageEntity(file2);
            product.addImageToProduct(image2);
        }
        if (file3.getSize() != 0) {
            image3 = toImageEntity(file3);
            product.addImageToProduct(image3);
        }
        if (file4.getSize() != 0) {
            image4 = toImageEntity(file4);
            product.addImageToProduct(image4);
        }

        log.info("Saving new Product. Title: {}; Author login: {}", product.getTitle(), product.getUser().getLogin()/*getAuthor()*/);
        Product productFromDB = productRepository.save(product); //присваиваем фотографию конкретному продукту
        productFromDB.setPreviewImageId(productFromDB.getImages().get(0).getId()); //получаем по 0му инлексу Первое фото, устанавливаем ему индекс 1 для сохранения в БД
        productRepository.save(product); // обновляем репозиторий,(добавляем продукт уже С ФОТО)
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
    public void deleteProductById(Long id) {  //удаление
        productRepository.deleteById(id);
    }

    @Override
    public Product getProductById(Long id) { //достаём продукт по id
        return productRepository.findById(id).orElse(null); //если товара с таким id  не найдено,ю то вернёт NULL
    }

    @Override
    public User getUserByPrincipal(Principal principal) {
        if (principal == null) {
            return new User();  // ноый Юзер, т.к. новому Юзеру НЕ БУДЕТ показана формочка Удаления товара
        } else {
            return userRepository.findByLogin(principal.getName());
        }
    }
}
