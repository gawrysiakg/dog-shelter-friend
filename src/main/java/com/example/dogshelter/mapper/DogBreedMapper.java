//package com.example.dogshelter.mapper;
//
//import com.example.dogshelter.domain.DogBreed;
//import com.example.dogshelter.dto.DogBreedDto;
//import lombok.Getter;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//@Getter
//public class DogBreedMapper {
//
////    public DogBreedDto mapToDogBreedDto(String breed) {
////        return new DogBreedDto(breed.toString());
////    }
//
//    public DogBreed mapToDogBreed(String dogBreed) {
//        if (dogBreed.equalsIgnoreCase("beagle")) {
//            return DogBreed.BEAGLE;
//        } else if (dogBreed.equalsIgnoreCase("boxer")) {
//            return DogBreed.BOXER;
//        } else if (dogBreed.equalsIgnoreCase("bull terrier")) {
//            return DogBreed.BULL_TERRIER;
//        } else if (dogBreed.equalsIgnoreCase("doberman")) {
//            return DogBreed.DOBERMAN;
//        } else if (dogBreed.equalsIgnoreCase("german shephard")) {
//            return DogBreed.GERMAN_SHEPHERD;
//        } else if (dogBreed.equalsIgnoreCase("husky")) {
//            return DogBreed.HUSKY;
//        } else if (dogBreed.equalsIgnoreCase("miniature pinscher")) {
//            return DogBreed.MINIATURE_PINSCHER;
//        } else if (dogBreed.equalsIgnoreCase("labrador retriever")) {
//            return DogBreed.LABRADOR_RETRIEVER;
//        } else if (dogBreed.equalsIgnoreCase("rottweiler")) {
//            return DogBreed.ROTTWEILER;
//        } else if (dogBreed.equalsIgnoreCase("saint bernard")) {
//            return DogBreed.SAINT_BERNARD;
//        } else if (dogBreed.equalsIgnoreCase("shih tzu")) {
//            return DogBreed.SHIH_TZU;
//        } else return DogBreed.MIXED;
//    }
//
////    public List<DogBreedDto> mapToDogBreedDtoList(List<DogBreed> dogList){
////        return dogList.stream().map(this::mapToDogBreedDto).collect(Collectors.toList());
////    }
////
////    public List<DogBreed> mapToDogBreedList(List<DogBreedDto> dogDtoList){
////        return dogDtoList.stream().map(this::mapToDogBreed).collect(Collectors.toList());
////    }
//}
