package com.pikachu.purple.application.user.port.in.user;

import com.pikachu.purple.domain.user.User;

public interface GetUserUseCase {

    Result find(Long userId);

    record Result(User user) {}

}
