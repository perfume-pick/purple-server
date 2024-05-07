package com.pikachu.purple.application.user.service.domain;

import com.pikachu.purple.domain.user.User;
import com.pikachu.purple.domain.user.enums.SocialLoginProvider;

public interface UserDomainService {

    void create(
        User createUser
    );

    void updateNicknameById(
        Long userId,
        String nickname
    );

    User findByEmailAndSocialLoginProvider(
        String email,
        SocialLoginProvider socialLoginProvider
    );

}
