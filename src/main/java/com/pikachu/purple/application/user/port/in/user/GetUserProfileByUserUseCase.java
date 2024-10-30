package com.pikachu.purple.application.user.port.in.user;

public interface GetUserProfileByUserUseCase {

    Result invoke();

    record Result(
        String nickname,
        String imageUrl,
        String email
    ) {}

}
