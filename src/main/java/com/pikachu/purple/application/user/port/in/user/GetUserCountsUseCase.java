package com.pikachu.purple.application.user.port.in.user;

public interface GetUserCountsUseCase {

    Result invoke();

    record Result(int userCounts) {}

}
