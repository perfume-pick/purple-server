package com.pikachu.purple.application.user.port.out;

import com.pikachu.purple.domain.user.entity.UserSearchHistory;
import java.util.List;

public interface UserSearchHistoryRepository {

    List<UserSearchHistory> findSearchHistoryByUserId(Long userId);

    void saveSearchHistory(
        Long userId,
        String keyword,
        String searchAt
    );

    void deleteAllSearchHistory(Long userId);

}
