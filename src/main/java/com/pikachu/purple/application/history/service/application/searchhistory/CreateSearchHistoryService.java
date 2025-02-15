package com.pikachu.purple.application.history.service.application.searchhistory;

import com.pikachu.purple.application.history.port.in.searchhistory.CreateSearchHistoryUseCase;
import com.pikachu.purple.application.history.service.domain.SearchHistoryDomainService;
import java.time.Instant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class CreateSearchHistoryService implements CreateSearchHistoryUseCase {

    private final SearchHistoryDomainService searchHistoryDomainService;

    @Override
    public void create(
        Long userId,
        String keyword,
        Instant searchAt
    ) {
        searchHistoryDomainService.validateNotExist(
            userId,
            keyword
        );

        searchHistoryDomainService.createSearchHistory(
            userId,
            keyword,
            searchAt
        );
    }
}
