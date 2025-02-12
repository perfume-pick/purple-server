package com.pikachu.purple.application.history.service.domain;

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

    void deleteAllSearchHistoryByUserId(Long userId);

    void validateNotExist(
        Long userId,
        String keyword
    );

}
