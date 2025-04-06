package com.pikachu.purple.infrastructure.objectstorage.adapter;

import com.pikachu.purple.application.user.port.out.ImageUploader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Component
public class ObjectStorageAdapter implements ImageUploader {

    @Override
    public String upload(Long userId, MultipartFile picture) {
        return null;
    }

    @Override
    public void delete(String fileName) {

    }

}
