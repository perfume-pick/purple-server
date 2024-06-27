package com.pikachu.purple.application.user.port.out;

import org.springframework.web.multipart.MultipartFile;

public interface ImageUrlS3Uploader {

    String upload(
        Long userId,
        MultipartFile picture
    );

    void delete(String fileName);

}
