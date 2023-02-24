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
    @GeneratedValue
    @Column(name = "ID", unique = true)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DOG_BREAD")
    private DogBreed dogBreed;

    @Column(name = "DOG_STATUS")
    private DogStatus dogStatus;

    @OneToMany(targetEntity = Walk.class,
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            mappedBy = "dog")
    private List<Walk> walksList=new ArrayList<>();

    public Dog(String name, DogBreed dogBreed, DogStatus dogStatus) {
    }
}
