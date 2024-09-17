package com.pikachu.purple.application.user.port.out;

import com.pikachu.purple.domain.user.User;
import com.pikachu.purple.domain.user.enums.SocialLoginProvider;

public interface UserRepository {

    User findByEmailAndSocialLoginProvider(
        String email,
        SocialLoginProvider socialLoginProvider
    );

    void create(User user);

    User update(User user);

    void validateNotExistedNickname(String nickname);

    int countAll();

    User findById(Long userId);

}
