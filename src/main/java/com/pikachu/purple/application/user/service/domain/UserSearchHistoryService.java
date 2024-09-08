package com.pikachu.purple.application.user.service.domain;

import com.pikachu.purple.domain.user.UserSearchHistory;
import java.time.LocalDateTime;
import java.util.List;

public interface UserSearchHistoryService {

    List<UserSearchHistory> findSearchHistoryByUserId(Long userId);

    void saveSearchHistory(
        Long userId,
        String keyword,
        LocalDateTime searchAt
    );

    void deleteAllSearchHistory(Long userId);

}
