package com.example.dogshelter.domain;

import java.util.Arrays;
import java.util.Optional;

public enum DogBreedFromString {
    MIXED("Mixed"),
    BEAGLE("Beagle"),
    BOXER("Boxer"),
    BULL_TERRIER("Bull terrier"),
    DOBERMAN("Doberman"),
    GERMAN_SHEPHERD("German shepard"),
    HUSKY("Husky"),
    LABRADOR_RETRIEVER("Labrador Retriever"),
    MINIATURE_PINSCHER("Miniature pincher"),
    ROTTWEILER("Rottweiler"),
    SAINT_BERNARD("Saint Bernard"),
    SHIH_TZU("Shih tzu");

    private  final String name;
    DogBreedFromString(String name){
        this.name=name;
    }


    DogBreedFromString dogBreedFromString(String name) {
        Optional<DogBreedFromString> dog = Arrays.stream(DogBreedFromString.values()).filter(dogBreedFromString -> dogBreedFromString.name().equalsIgnoreCase(name)).findFirst();
       return  dog.isPresent() ? dog.get() : DogBreedFromString.valueOf(name);
    }
}
