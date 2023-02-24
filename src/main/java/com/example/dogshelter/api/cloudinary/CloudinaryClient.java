package com.example.dogshelter.api.cloudinary;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.dogshelter.domain.Image;
import com.example.dogshelter.repository.CloudinaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Autowired
    public CloudinaryClient(CloudinaryRepository cloudinaryRepository, CloudinaryConfig cloudinaryConfig){
            this.cloudinaryRepository=cloudinaryRepository;
            this.cloudinaryConfig=cloudinaryConfig;
            cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", cloudinaryConfig.getCloudNameValue(),
                "api_key", cloudinaryConfig.getApiKeyValue(),
                "api_secret", cloudinaryConfig.getApiSecretValue()));
    }


    public String uploadFileAndSaveToDb(String path) {
        File file = new File(path);
        Map uploadResult = null;
        try {
            uploadResult = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
            cloudinaryRepository.save(new Image(uploadResult.get("url").toString()));
        } catch (IOException e) {
            e.printStackTrace();
            // todo
        }
        return uploadResult.get("url").toString();
    }






}
