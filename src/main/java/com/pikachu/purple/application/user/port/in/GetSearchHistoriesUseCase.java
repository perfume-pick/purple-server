package com.pikachu.purple.application.user.port.in;

import com.pikachu.purple.domain.history.SearchHistory;
import java.util.List;

public interface GetSearchHistoriesUseCase {

    Result invoke();

    record Result(List<SearchHistory> searchHistories){}

}
