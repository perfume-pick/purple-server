package com.pikachu.purple.application.user.port.out;

import com.pikachu.purple.domain.user.UserSearchHistory;
import java.time.LocalDateTime;
import java.util.List;

public interface UserSearchHistoryRepository {

    List<UserSearchHistory> findSearchHistoryByUserId(Long userId);

    void saveSearchHistory(
        Long userId,
        String keyword,
        LocalDateTime searchAt
    );

    void deleteAllSearchHistory(Long userId);

}
