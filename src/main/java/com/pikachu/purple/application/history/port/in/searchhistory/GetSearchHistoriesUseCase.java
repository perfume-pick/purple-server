package com.pikachu.purple.application.history.port.in.searchhistory;

import com.pikachu.purple.application.history.common.dto.SearchHistoryDTO;
import java.util.List;

public interface GetSearchHistoriesUseCase {

    Result findAll(Long userId);

    record Result(List<SearchHistoryDTO> searchHistories){}

}
