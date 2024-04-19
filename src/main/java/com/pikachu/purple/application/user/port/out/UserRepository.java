package com.pikachu.purple.application.user.port.out;

public interface UserRepository {

    void validateNotExistedEmail(String email);

}
