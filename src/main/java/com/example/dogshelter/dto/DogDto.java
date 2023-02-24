package com.example.dogshelter.dto;

import com.example.dogshelter.domain.DogBreed;
import com.example.dogshelter.domain.DogStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DogDto {

    private Long id;
    private String name;
    private DogBreed dogBreed;
    private DogStatus dogStatus;
}
