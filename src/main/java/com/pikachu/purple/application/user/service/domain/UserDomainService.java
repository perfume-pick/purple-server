package com.pikachu.purple.application.user.service.domain;


import com.pikachu.purple.domain.user.entity.User;
import com.pikachu.purple.domain.user.enums.SocialLoginProvider;

public interface UserDomainService {

    void create(User createdUser);

    void updateNickname(
        Long userId,
        String nickname
    );

    User findByEmailAndSocialLoginProvider(
        String email,
        SocialLoginProvider socialLoginProvider
    );

    int countAll();

}
