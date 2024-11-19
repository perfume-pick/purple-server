package com.pikachu.purple.application.user.port.in.user;

public interface DeleteUserUseCase {

    Result invoke();

    record Result(String clientUrl) {}

}
