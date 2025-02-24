package com.pikachu.purple.application.history.service.application.searchhistory;

import com.pikachu.purple.application.history.common.dto.SearchHistoryDTO;
import com.pikachu.purple.application.history.port.in.searchhistory.GetSearchHistoriesUseCase;
import com.pikachu.purple.application.history.service.domain.SearchHistoryDomainService;
import com.pikachu.purple.domain.history.SearchHistory;
import java.util.List;
import java.util.stream.IntStream;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class GetSearchHistoriesService implements GetSearchHistoriesUseCase {

    private final SearchHistoryDomainService searchHistoryDomainService;

    @Override
    public Result findAll(Long userId) {
        List<SearchHistory> searchHistories = searchHistoryDomainService.findAllByUserId(userId);

        List<SearchHistoryDTO> searchHistoryDTOs = IntStream.range(0, searchHistories.size())
            .mapToObj(i -> SearchHistoryDTO.of(
                i + 1,
                searchHistories.get(i).getKeyword()
            ))
            .toList();

        return new Result(searchHistoryDTOs);
    }

}
