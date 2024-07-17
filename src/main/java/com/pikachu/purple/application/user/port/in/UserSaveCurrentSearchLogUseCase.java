package com.pikachu.purple.application.user.port.in;

public interface UserSaveCurrentSearchLogUseCase {

    void invoke(
        String keyword,
        String searchAt
    );


}
