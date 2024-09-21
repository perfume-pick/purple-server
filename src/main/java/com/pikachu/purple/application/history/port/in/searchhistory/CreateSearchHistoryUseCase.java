package com.pikachu.purple.application.history.port.in.searchhistory;

import java.time.Instant;

public interface CreateSearchHistoryUseCase {

    void invoke(
        String keyword,
        Instant searchAt
    );

}
