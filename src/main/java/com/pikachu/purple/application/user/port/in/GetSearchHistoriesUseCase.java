package com.pikachu.purple.application.user.port.in;

import com.pikachu.purple.application.user.common.dto.SearchHistoryDTO;
import java.util.List;

public interface GetSearchHistoriesUseCase {

    Result invoke();

    record Result(List<SearchHistoryDTO> searchHistories){}

}
