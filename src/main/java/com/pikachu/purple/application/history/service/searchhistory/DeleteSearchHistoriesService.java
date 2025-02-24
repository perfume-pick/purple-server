package com.pikachu.purple.application.history.service.searchhistory;

import com.pikachu.purple.application.history.port.in.searchhistory.DeleteSearchHistoriesUseCase;
import com.pikachu.purple.application.history.port.out.SearchHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
class DeleteSearchHistoriesService implements
    DeleteSearchHistoriesUseCase {

    private final SearchHistoryRepository searchHistoryRepository;

    @Transactional
    @Override
    public void deleteAll(Long userId) {
        searchHistoryRepository.deleteAllSearchHistoryByUserId(userId);
    }

}
