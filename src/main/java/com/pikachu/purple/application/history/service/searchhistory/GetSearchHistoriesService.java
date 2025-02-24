package com.pikachu.purple.application.history.service.searchhistory;

import com.pikachu.purple.application.history.common.dto.SearchHistoryDTO;
import com.pikachu.purple.application.history.port.in.searchhistory.GetSearchHistoriesUseCase;
import com.pikachu.purple.application.history.port.out.SearchHistoryRepository;
import com.pikachu.purple.domain.history.SearchHistory;
import java.util.List;
import java.util.stream.IntStream;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
class GetSearchHistoriesService implements GetSearchHistoriesUseCase {

    private final SearchHistoryRepository searchHistoryRepository;

    @Override
    public Result findAll(Long userId) {
        List<SearchHistory> searchHistories = searchHistoryRepository.findAllByUserId(userId);

        List<SearchHistoryDTO> searchHistoryDTOs = IntStream.range(0, searchHistories.size())
            .mapToObj(i -> SearchHistoryDTO.of(
                i + 1,
                searchHistories.get(i).getKeyword()
            ))
            .toList();

        return new Result(searchHistoryDTOs);
    }

}
