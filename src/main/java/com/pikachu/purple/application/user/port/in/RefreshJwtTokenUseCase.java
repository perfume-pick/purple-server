package com.pikachu.purple.application.user.port.in;

public interface RefreshJwtTokenUseCase {

    Result invoke(String jwtToken);

    record Result(String jwtToken) {}

}
