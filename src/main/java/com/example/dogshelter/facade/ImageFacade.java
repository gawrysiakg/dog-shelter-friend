package com.example.dogshelter.facade;

import com.example.dogshelter.domain.Image;
import com.example.dogshelter.dto.ImageDto;
import com.example.dogshelter.mapper.ImageMapper;
import com.example.dogshelter.repository.CloudinaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@RequiredArgsConstructor
public class ImageFacade {

    private final CloudinaryRepository cloudinaryRepository;
    private final ImageMapper imageMapper;

    public List<ImageDto> findAll() {
        List <Image> images = cloudinaryRepository.findAll();
        return imageMapper.mapToImageDtoList(images);
    }
}
