package com.pikachu.purple.application.history.service.application.searchhistory;

import com.pikachu.purple.application.history.port.in.searchhistory.DeleteSearchHistoriesUseCase;
import com.pikachu.purple.application.history.service.domain.SearchHistoryDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class DeleteSearchHistoriesService implements
    DeleteSearchHistoriesUseCase {

    private final SearchHistoryDomainService searchHistoryDomainService;

    @Override
    public void deleteAll(Long userId) {
        searchHistoryDomainService.deleteAllSearchHistoryByUserId(userId);
    }

}
