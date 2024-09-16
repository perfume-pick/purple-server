package com.pikachu.purple.bootstrap.user.dto.response;

import com.pikachu.purple.domain.history.SearchHistory;
import java.util.List;

public record GetSearchHistoriesResponse(List<SearchHistory> userSearchHistories) {}
