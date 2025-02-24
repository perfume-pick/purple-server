package com.pikachu.purple.application.user.port.in.useraccord;

public interface CreateUserAccordUseCase {

    void createAll(
        Long userId,
        Long perfumeId
    );

}
