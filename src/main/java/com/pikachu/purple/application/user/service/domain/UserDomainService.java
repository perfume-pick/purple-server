package com.pikachu.purple.application.user.service.domain;


import com.pikachu.purple.domain.user.User;
import com.pikachu.purple.domain.user.enums.SocialLoginProvider;
import org.springframework.web.multipart.MultipartFile;

public interface UserDomainService {

    void create(
        String email,
        String nickname,
        SocialLoginProvider socialLoginProvider
    );

    User updateProfile(
        Long userId,
        String nickname,
        boolean isChanged,
        MultipartFile picture
    );

    User findByEmailAndSocialLoginProvider(
        String email,
        SocialLoginProvider socialLoginProvider
    );

    int countAll();

    User findById(Long userId);

}
