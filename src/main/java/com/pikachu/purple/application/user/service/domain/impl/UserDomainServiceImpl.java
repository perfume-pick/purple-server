package com.pikachu.purple.application.user.service.domain.impl;

import com.pikachu.purple.application.user.port.out.ImageUrlS3Uploader;
import com.pikachu.purple.application.user.port.out.UserRepository;
import com.pikachu.purple.application.user.service.domain.UserDomainService;
import com.pikachu.purple.domain.user.entity.User;
import com.pikachu.purple.domain.user.enums.SocialLoginProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class UserDomainServiceImpl implements UserDomainService {

    private final UserRepository userRepository;
    private final ImageUrlS3Uploader imageUrlS3Uploader;

    @Override
    public void create(User createdUser) {
        User user = User.builder()
            .id(createdUser.getId())
            .email(createdUser.getEmail())
            .nickname(createdUser.getNickname())
            .imageUrl(createdUser.getImageUrl())
            .registeredAt(createdUser.getRegisteredAt())
            .socialLoginProvider(createdUser.getSocialLoginProvider())
            .build();

        userRepository.save(user);
    }

    @Override
    public User getById(Long userId) {
        return userRepository.getById(userId);
    }

    @Override
    public void updateProfile(
        Long userId,
        String nickname,
        MultipartFile picture
    ) {
        User user = userRepository.getById(userId);

        userRepository.validateNotExistedNickname(nickname);
        user.updateNickname(nickname);

        if(!user.getImageUrl().isEmpty()) imageUrlS3Uploader.delete(user.getImageUrl());

        if(picture == null || picture.isEmpty()) user.updateImageUrl("");
        else{
            String imageUrl = imageUrlS3Uploader.upload(userId, picture);
            user.updateImageUrl(imageUrl);
        }
        userRepository.save(user);
    }

    @Override
    public User findByEmailAndSocialLoginProvider(
        String email,
        SocialLoginProvider socialLoginProvider
    ) {
        return userRepository.findByEmailAndSocialLoginProvider(
            email,
            socialLoginProvider
        );
    }

    @Override
    public int countAll() {
        return userRepository.countAll();
    }

}
