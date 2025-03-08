package com.pikachu.purple.application.history.port.out;

import com.pikachu.purple.domain.history.SearchHistory;
import java.time.Instant;
import java.util.List;

public interface SearchHistoryRepository {

    List<SearchHistory> findAllByUserId(Long userId);

    void create(
        SearchHistory searchHistory,
        Instant searchAt
    );

    void deleteAll(Long userId);

    void validateNotExist(Long userId, String keyword);

}
