package com.pikachu.purple.application.user.port.in;

import com.pikachu.purple.domain.user.User;

public interface GetUserByIdUseCase {

    Result invoke(Command command);

    record Command(Long userId) {}

    record Result(User user) {}

}
