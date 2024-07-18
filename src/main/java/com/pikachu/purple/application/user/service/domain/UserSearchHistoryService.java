package com.pikachu.purple.application.user.service.domain;

import com.pikachu.purple.domain.user.entity.UserSearchHistory;
import java.util.List;

public interface UserSearchHistoryService {

    List<UserSearchHistory> findSearchHistoryByUserId(Long userId);

    void saveSearchHistory(
        Long userId,
        String keyword,
        String searchAt
    );

    void deleteAllSearchHistory(Long userId);

}
