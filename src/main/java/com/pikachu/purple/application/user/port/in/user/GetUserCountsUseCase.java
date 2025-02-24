package com.pikachu.purple.application.user.port.in.user;

public interface GetUserCountsUseCase {

    Result count();

    record Result(int userCounts) {}

}
