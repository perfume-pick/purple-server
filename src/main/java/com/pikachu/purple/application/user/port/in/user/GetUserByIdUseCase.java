package com.pikachu.purple.application.user.port.in.user;

import com.pikachu.purple.domain.user.User;

public interface GetUserByIdUseCase {

    Result invoke(Long userId);

    record Result(User user) {}

}
