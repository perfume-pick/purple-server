package com.pikachu.purple.application.user.port.in;

import java.time.Instant;

public interface CreateSearchHistoryUseCase {

    void invoke(
        String keyword,
        Instant searchAt
    );

}
