package com.pikachu.purple.application.history.port.in.searchhistory;

import com.pikachu.purple.application.user.common.dto.SearchHistoryDTO;
import java.util.List;

public interface GetSearchHistoriesUseCase {

    Result invoke();

    record Result(List<SearchHistoryDTO> searchHistories){}

}
