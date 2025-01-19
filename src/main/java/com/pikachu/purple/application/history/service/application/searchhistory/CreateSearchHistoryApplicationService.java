package com.pikachu.purple.application.history.service.application.searchhistory;

import static com.pikachu.purple.support.security.SecurityProvider.getCurrentUserAuthentication;

import com.pikachu.purple.application.history.port.in.searchhistory.CreateSearchHistoryUseCase;
import com.pikachu.purple.application.history.service.domain.SearchHistoryDomainService;
import java.time.Instant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class CreateSearchHistoryApplicationService implements CreateSearchHistoryUseCase {

    private final SearchHistoryDomainService searchHistoryDomainService;

    @Override
    public void invoke(
        String keyword,
        Instant searchAt
    ) {
        Long userId = getCurrentUserAuthentication().userId();

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
