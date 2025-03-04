package com.pikachu.purple.application.history.service.searchhistory;

import com.pikachu.purple.application.history.port.in.searchhistory.GetSearchHistoriesUseCase;
import com.pikachu.purple.application.history.port.out.SearchHistoryRepository;
import com.pikachu.purple.domain.history.SearchHistory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
class GetSearchHistoriesService implements GetSearchHistoriesUseCase {

    private final SearchHistoryRepository searchHistoryRepository;

    @Transactional
    @Override
    public Result findAllByUserId(Long userId) {
        List<SearchHistory> searchHistories = searchHistoryRepository.findAllByUserId(userId);

        return new Result(searchHistories);
    }

}
