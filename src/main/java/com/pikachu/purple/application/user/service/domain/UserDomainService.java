package com.pikachu.purple.application.user.service.domain;


import com.pikachu.purple.domain.user.entity.User;
import com.pikachu.purple.domain.user.enums.SocialLoginProvider;
import org.springframework.web.multipart.MultipartFile;

public interface UserDomainService {

    void create(User createdUser);

    void updateProfile(
        Long userId,
        String nickname,
        boolean isChanged,
        MultipartFile picture
    );

    User getById(Long userId);

    User findByEmailAndSocialLoginProvider(
        String email,
        SocialLoginProvider socialLoginProvider
    );

    int countAll();

}
