package com.example.dogshelter.api.cloudinary;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.dogshelter.domain.Image;
import com.example.dogshelter.dto.ImageDto;
import com.example.dogshelter.mapper.ImageMapper;
import com.example.dogshelter.repository.CloudinaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.io.File;
import java.io.IOException;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class CloudinaryClient {


    private Cloudinary cloudinary;
    private CloudinaryRepository cloudinaryRepository;
    private CloudinaryConfig cloudinaryConfig;
    private ImageMapper imageMapper;

    @Autowired
    public CloudinaryClient(CloudinaryRepository cloudinaryRepository, CloudinaryConfig cloudinaryConfig, ImageMapper imageMapper){
            this.cloudinaryRepository=cloudinaryRepository;
            this.cloudinaryConfig=cloudinaryConfig;
            this.imageMapper=imageMapper;
            cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", cloudinaryConfig.getCloudNameValue(),
                "api_key", cloudinaryConfig.getApiKeyValue(),
                "api_secret", cloudinaryConfig.getApiSecretValue()));
    }


    public ImageDto uploadFileAndSaveToDb(String path) throws IOException {
        File file = new File(path);
        Map uploadResult = null;
            uploadResult = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
            Image savedImage = cloudinaryRepository.save(new Image(uploadResult.get("url").toString()));
            return imageMapper.mapToImageDto(savedImage);
    }






}
