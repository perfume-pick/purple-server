package com.pikachu.purple.application.history.port.in.searchhistory;

import java.time.Instant;

public interface CreateSearchHistoryUseCase {

    void create(
        Long userId,
        String keyword,
        Instant searchAt
    );

}
