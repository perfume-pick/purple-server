package com.pikachu.purple.application.user.service.application;

import static com.pikachu.purple.support.security.SecurityProvider.getCurrentUserAuthentication;

import com.pikachu.purple.application.user.port.in.DeleteSearchHistoriesUseCase;
import com.pikachu.purple.application.user.service.domain.SearchHistoryDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteSearchHistoriesApplicationService implements
    DeleteSearchHistoriesUseCase {

    private final SearchHistoryDomainService searchHistoryDomainService;

    @Override
    public void invoke() {
        Long userId = getCurrentUserAuthentication().userId();
        searchHistoryDomainService.deleteAllSearchHistoryByUserId(userId);
    }

}