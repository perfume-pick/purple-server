package com.pikachu.purple.application.user.port.in;

public interface RefreshJwtTokenUseCase {

    RefreshJwtTokenUseCase.Result invoke(String jwtToken);

    record Result(String jwtToken) {}

}
