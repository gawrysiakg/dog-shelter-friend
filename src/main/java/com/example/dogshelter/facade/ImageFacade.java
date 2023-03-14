package com.example.dogshelter.facade;

import com.example.dogshelter.domain.Image;
import com.example.dogshelter.dto.ImageDto;
import com.example.dogshelter.mapper.ImageMapper;
import com.example.dogshelter.service.CloudinaryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.List;
@Slf4j
@Component
@RequiredArgsConstructor
public class ImageFacade {

    private final ImageMapper imageMapper;
    private final CloudinaryService cloudinaryService;

    public List<ImageDto> findAll() {
        List <Image> images = cloudinaryService.findAll();
        return imageMapper.mapToImageDtoList(images);
    }

    public ImageDto uploadImage(ImageDto imageDto) throws IOException {
        log.info("Uploading image.");
        return cloudinaryService.uploadAndSaveToDb(imageDto.getUrl());
    }

    public void deleteImage(String url){
        log.info("Deleting image.");
        cloudinaryService.deleteImage(url);
    }
}
