package com.example.dogshelter.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "IMAGE")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true)
    private Long id;
    @Column(name = "IMAGE_URL")
    private String imageAddress;

    public Image(String imageAddress) {
        this.imageAddress = imageAddress;
   }

    public Image() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImageAdress() {
        return imageAddress;
    }

    public void setImageAdress(String imageAdress) {
        this.imageAddress = imageAdress;
    }

}
