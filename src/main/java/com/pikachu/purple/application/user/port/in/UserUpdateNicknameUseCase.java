package com.pikachu.purple.application.user.port.in;

public interface UserUpdateNicknameUseCase {

    void invoke(Command command);

    record Command(String nickname) {

    }

}
