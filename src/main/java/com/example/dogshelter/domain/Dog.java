package com.example.dogshelter.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "DOGS")
public class Dog {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", unique = true)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DOG_BREAD")
    private String dogBreed;

    @Column(name = "IN_SHELTER")
    private boolean inShelter;


    @OneToMany(targetEntity = Walk.class,
           // cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            mappedBy = "dog")
    private List<Walk> walksList=new ArrayList<>();

    public Dog(String name, String dogBreed, boolean inShelter) {
        this.name = name;
        this.dogBreed = dogBreed;
        this.inShelter = inShelter;
    }

    public Dog(Long id, String name, String dogBreed, boolean inShelter) {
        this.id = id;
        this.name = name;
        this.dogBreed = dogBreed;
        this.inShelter = inShelter;
    }
}
