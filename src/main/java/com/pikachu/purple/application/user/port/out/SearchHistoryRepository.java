package com.pikachu.purple.application.user.port.out;

import com.pikachu.purple.domain.history.SearchHistory;
import java.time.Instant;
import java.util.List;

public interface SearchHistoryRepository {

    List<SearchHistory> findAllByUserId(Long userId);

    void createSearchHistory(
        Long userId,
        String keyword,
        Instant searchAt
    );

    void deleteAllSearchHistory(Long userId);

}
