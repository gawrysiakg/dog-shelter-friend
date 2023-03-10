package com.example.dogshelter.facade;

import com.example.dogshelter.domain.Image;
import com.example.dogshelter.dto.ImageDto;
import com.example.dogshelter.mapper.ImageMapper;
import com.example.dogshelter.repository.CloudinaryRepository;
import com.example.dogshelter.service.CloudinaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;
import java.util.List;
@Component
@RequiredArgsConstructor
public class ImageFacade {

    private final CloudinaryRepository cloudinaryRepository;
    private final ImageMapper imageMapper;
    private final CloudinaryService cloudinaryService;

    public List<ImageDto> findAll() {
        List <Image> images = cloudinaryRepository.findAll();
        return imageMapper.mapToImageDtoList(images);
    }

    public ImageDto uploadImage(ImageDto imageDto) throws IOException {
        return cloudinaryService.uploadAndSaveToDb(imageDto.getUrl());
    }

    public void deleteImage(String url){
        cloudinaryService.deleteImage(url);
    }
}
