package com.pikachu.purple.infrastructure.objectstorage.adapter;

import com.pikachu.purple.application.user.port.out.ImageUploader;
import com.pikachu.purple.bootstrap.common.exception.BusinessException;
import java.util.Objects;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@RequiredArgsConstructor
@Component
public class ImageUrlS3Adaptor implements ImageUploader {

    private final S3Client s3Client;

    @Value("${spring.cloud.aws.s3.bucket}")
    private String bucket;

    public String upload(
        Long userId,
        MultipartFile file
    ) {
        fileValidator(file);
        extensionValidator(file);
        String fileName = UUID.randomUUID().toString();
        String key = userId + ":" + fileName;

        try {
            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucket)
                .key(key)
                .contentType(file.getContentType())
                .build();

            s3Client.putObject(putObjectRequest, RequestBody.fromBytes(file.getBytes()));
        } catch (Exception e) {
            throw BusinessException.FileUploadFailException;
        }

        return key;
    }

    public void delete(String fileName) {
        try{
            DeleteObjectRequest deleteObjectRequest = DeleteObjectRequest.builder()
                .bucket(bucket)
                .key(fileName)
                .build();
            s3Client.deleteObject(deleteObjectRequest);
        } catch (Exception e){
            throw BusinessException.FileDeleteFailException;
        }
    }

    private void fileValidator(MultipartFile file){
        if(file.isEmpty() || Objects.isNull(file.getOriginalFilename())) {
            throw BusinessException.FileEmptyException;
        }
    }

    private void extensionValidator(MultipartFile file){
        String contentType = file.getContentType();

        if(ObjectUtils.isEmpty(contentType) || (!contentType.contains("image/jpeg") && !contentType.contains("image/png") && !contentType.contains("image/jpg"))) {
            throw BusinessException.InvalidFileExtensionException;
        }
    }

}
