package com.pikachu.purple.application.user.service.domain;


import com.pikachu.purple.domain.user.User;
import com.pikachu.purple.domain.user.enums.SocialLoginProvider;

public interface UserDomainService {

    void create(
        String email,
        String nickname,
        SocialLoginProvider socialLoginProvider
    );

    User findByEmailAndSocialLoginProvider(
        String email,
        SocialLoginProvider socialLoginProvider
    );

    int count();

    User findById(Long userId);

}
