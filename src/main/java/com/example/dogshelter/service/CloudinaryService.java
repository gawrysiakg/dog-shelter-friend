package com.example.dogshelter.service;

import com.example.dogshelter.api.cloudinary.CloudinaryClient;
import com.example.dogshelter.config.AdminConfig;
import com.example.dogshelter.domain.Mail;
import com.example.dogshelter.dto.ImageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CloudinaryService {

    private final CloudinaryClient cloudinaryClient;
    private final SimpleEmailService simpleEmailService;
    private final AdminConfig adminConfig;
    public static final String UPLOAD_SUBJECT = "New image uploaded!";



    public ImageDto uploadAndSaveToDb(String path) throws IOException {
        ImageDto imageDto = cloudinaryClient.uploadFileAndSaveToDb(path);

        Optional.ofNullable(imageDto).ifPresent((image)->
                    simpleEmailService.send(
                         new Mail.MailBuilder()
                            .mailTo(adminConfig.getAdminMail())
                            .subject(UPLOAD_SUBJECT)
                            .message("New image uploaded. You can find it in Cloudinary service, in shelter gallery" +
                                    "or by url: "+imageDto.getUrl())
                            .build()));
        return imageDto;
    }



}
