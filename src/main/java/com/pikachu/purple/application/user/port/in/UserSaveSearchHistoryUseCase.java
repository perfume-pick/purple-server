package com.pikachu.purple.application.user.port.in;

import java.time.LocalDateTime;

public interface UserSaveSearchHistoryUseCase {

    void invoke(
        String keyword,
        LocalDateTime searchAt
    );

}
