package com.pikachu.purple.application.history.service.searchhistory;

import com.pikachu.purple.application.history.port.in.searchhistory.CreateSearchHistoryUseCase;
import com.pikachu.purple.application.history.port.out.SearchHistoryRepository;
import com.pikachu.purple.domain.history.SearchHistory;
import java.time.Instant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
class CreateSearchHistoryService implements CreateSearchHistoryUseCase {

    private final SearchHistoryRepository searchHistoryRepository;

    @Override
    public void create(
        Long userId,
        String keyword,
        Instant searchAt
    ) {
        searchHistoryRepository.validateNotExist(
            userId,
            keyword
        );

        SearchHistory searchHistory = SearchHistory.builder()
            .id(userId)
            .keyword(keyword)
            .build();

        searchHistoryRepository.create(
            searchHistory,
            searchAt
        );
    }

}
