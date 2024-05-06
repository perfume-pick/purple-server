package com.pikachu.purple.application.user.port.out;

import com.pikachu.purple.domain.user.User;

public interface UserRepository {

    User validateNotExistedEmail(String email);

    void save(User user);

    User getById(Long userId);

    void validateNotExistedNickName(String nickName);

}
