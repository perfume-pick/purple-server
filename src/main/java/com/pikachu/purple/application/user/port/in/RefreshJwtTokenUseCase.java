package com.pikachu.purple.application.user.port.in;

public interface RefreshJwtTokenUseCase {

    RefreshJwtTokenUseCase.Result invoke(RefreshJwtTokenUseCase.Command command);

    record Command(
        String jwtToken
    ) {

    }

    record Result(
        String jwtToken
    ) {

    }

}
