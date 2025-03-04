package com.pikachu.purple.application.history.port.in.searchhistory;

import com.pikachu.purple.domain.history.SearchHistory;
import java.util.List;

public interface GetSearchHistoriesUseCase {

    Result findAllByUserId(Long userId);

    record Result(List<SearchHistory> searchHistories){}

}
