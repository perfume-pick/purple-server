package com.pikachu.purple.application.user.service.domain;

import com.pikachu.purple.domain.history.SearchHistory;
import java.time.Instant;
import java.util.List;

public interface SearchHistoryDomainService {

    List<SearchHistory> findAllByUserId(Long userId);

    void createSearchHistory(
        Long userId,
        String keyword,
        Instant searchAt
    );

    void deleteAllSearchHistory(Long userId);

}
