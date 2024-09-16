package com.pikachu.purple.application.user.service.application;

import static com.pikachu.purple.support.security.SecurityProvider.getCurrentUserAuthentication;

import com.pikachu.purple.application.user.port.in.CreateSearchHistoryUseCase;
import com.pikachu.purple.application.user.service.domain.SearchHistoryDomainService;
import java.time.Instant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateSearchHistoryApplicationService implements CreateSearchHistoryUseCase {

    private final SearchHistoryDomainService searchHistoryDomainService;

    @Override
    public void invoke(
        String keyword,
        Instant searchAt
    ) {
        Long userId = getCurrentUserAuthentication().userId();
        searchHistoryDomainService.createSearchHistory(
            userId,
            keyword,
            searchAt
        );
    }
}
