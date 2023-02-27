package com.example.dogshelter.mapper;

import com.example.dogshelter.domain.Image;
import com.example.dogshelter.dto.ImageDto;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Getter
public class ImageMapper {

    public ImageDto mapToImageDto(Image image){
        return new ImageDto(image.getImageAddress());
    }

    public List<ImageDto> mapToImageDtoList(List<Image> imageList){
        return imageList.stream().map(this::mapToImageDto).collect(Collectors.toList());
    }
}
