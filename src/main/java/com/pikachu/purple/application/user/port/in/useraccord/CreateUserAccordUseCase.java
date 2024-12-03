package com.pikachu.purple.application.user.port.in.useraccord;

public interface CreateUserAccordUseCase {

    void invoke(Command command);

    record Command(Long perfumeId) {}

}
