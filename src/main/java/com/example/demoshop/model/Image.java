package com.example.demoshop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "images")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "img_id", unique = true)
    private Long id;
    @Column(name = "img_name")
    private String imgName;
    @Column(name = "img_originalFileName")
    private String originalFileName; //параметр для передачи фотографий
    @Column(name = "img_siza")//размер файла
    private Long size;
    @Column(name = "img_Type")
    private String contentType;     //расширение файла
    @Column(name = "img_isPreview")
    private boolean isPreviewImage;  //"Флажёк" - что фото является НАЧАЛЬНЫМ основным

    @Lob
    private byte[] bytes;  //массик байтов, для храннеия фото

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private Product product;

}
