package com.pikachu.purple.application.user.service.domain.impl;

import com.pikachu.purple.application.user.port.out.ImageUrlS3Uploader;
import com.pikachu.purple.application.user.port.out.UserRepository;
import com.pikachu.purple.application.user.service.domain.UserDomainService;
import com.pikachu.purple.application.util.IdUtil;
import com.pikachu.purple.domain.user.User;
import com.pikachu.purple.domain.user.enums.SocialLoginProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class UserDomainServiceImpl implements UserDomainService {

    private static final String NICKNAME_ANONYMIZATION = "익명의 사용자";
    private static final String STRING_DEFAULT = "";
    private final UserRepository userRepository;
    private final ImageUrlS3Uploader imageUrlS3Uploader;

    @Override
    public void create(
        String email,
        String nickname,
        SocialLoginProvider socialLoginProvider
    ) {
        User user = User.builder()
            .id(IdUtil.generateId())
            .email(email)
            .nickname(nickname)
            .socialLoginProvider(socialLoginProvider)
            .build();

        userRepository.create(user);
    }

    @Override
    public User updateProfile(
        Long userId,
        String nickname,
        boolean isChanged,
        MultipartFile picture
    ) {
        User user = userRepository.findById(userId);

        isValidToUpdateNickname(
            user,
            nickname
        );

        isValidToUpdateImage(
            user,
            isChanged,
            picture
        );

        return userRepository.update(user);
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
        String imageUrl = user.getImageUrl();
        if (imageUrl != null && !imageUrl.isEmpty()) {
            imageUrlS3Uploader.delete(imageUrl);
        }
    }

    private String changeImage(Long userId, MultipartFile picture) {
        if (picture == null || picture.isEmpty()) return STRING_DEFAULT;
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
    public int count() {
        return userRepository.count();
    }

    @Override
    public User findById(Long userId) {
        return userRepository.findById(userId);
    }

    @Override
    public void delete(Long userId) {
        User user = userRepository.findById(userId);

        deleteExistingImage(user);

        user.updateEmail(STRING_DEFAULT);
        user.updateNickname(NICKNAME_ANONYMIZATION);
        user.updateImageUrl(STRING_DEFAULT);

        userRepository.update(user);
    }

}
