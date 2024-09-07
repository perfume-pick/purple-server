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

    private static final String IMAGE_DEFAULT = "";
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
    public User updateProfile(
        Long userId,
        String nickname,
        boolean isChanged,
        MultipartFile picture
    ) {
        User user = userRepository.getById(userId);

        isValidToUpdateNickname(
            user,
            nickname
        );
        isValidToUpdateImage(
            user,
            isChanged,
            picture
        );

        return userRepository.save(user);
    }

    private void isValidToUpdateNickname(
        User user,
        String nickname
    ) {
        if (!user.getNickname().equals(nickname)) {
            userRepository.validateNotExistedNickname(nickname);
            user.updateNickname(nickname);
        }
    }

    private void isValidToUpdateImage(
        User user,
        Boolean isChanged,
        MultipartFile picture
    ) {
        if (!isChanged) return;

        deleteExistingImage(user);

        String newImageUrl = changeImage(
            user.getId(),
            picture
        );
        user.updateImageUrl(newImageUrl);
    }

    private void deleteExistingImage(User user) {
        if (!user.getImageUrl().isEmpty()) imageUrlS3Uploader.delete(user.getImageUrl());
    }

    private String changeImage(Long userId, MultipartFile picture) {
        if (picture == null || picture.isEmpty()) return IMAGE_DEFAULT;
        return imageUrlS3Uploader.upload(
            userId,
            picture
        );
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
