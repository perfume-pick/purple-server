package com.pikachu.purple.application.history.service.application.searchhistory;

import static com.pikachu.purple.support.security.SecurityProvider.getCurrentUserAuthentication;

import com.pikachu.purple.application.history.port.in.searchhistory.DeleteSearchHistoriesUseCase;
import com.pikachu.purple.application.history.service.domain.SearchHistoryDomainService;
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
