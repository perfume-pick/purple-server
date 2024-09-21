package com.pikachu.purple.application.user.service.application;

import static com.pikachu.purple.support.security.SecurityProvider.getCurrentUserAuthentication;

import com.pikachu.purple.application.user.common.dto.SearchHistoryDTO;
import com.pikachu.purple.application.user.port.in.GetSearchHistoriesUseCase;
import com.pikachu.purple.application.user.service.domain.SearchHistoryDomainService;
import com.pikachu.purple.domain.history.SearchHistory;
import java.util.List;
import java.util.stream.IntStream;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetSearchHistoriesApplicationService implements GetSearchHistoriesUseCase {

    private final SearchHistoryDomainService searchHistoryDomainService;

    @Override
    public Result invoke() {
        Long userId = getCurrentUserAuthentication().userId();

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
