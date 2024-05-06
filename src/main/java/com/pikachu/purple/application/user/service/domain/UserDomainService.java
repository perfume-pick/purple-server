package com.pikachu.purple.application.user.service.domain;

import com.pikachu.purple.domain.user.User;

public interface UserDomainService {

    void create(
        User createUser
    );

    void updateNickNameById(
        Long userId,
        String nickName
    );

}
