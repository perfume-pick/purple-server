package com.pikachu.purple.application.user.port.out;

import com.pikachu.purple.domain.user.User;
import com.pikachu.purple.domain.user.enums.SocialLoginProvider;

public interface UserRepository {

    User findByEmailAndSocialLoginProvider(
        String email,
        SocialLoginProvider socialLoginProvider
    );

    void validateNotExistedEmail(String email);

    void save(User user);

    User getById(Long userId);

    void validateNotExistedNickname(String nickname);

}
