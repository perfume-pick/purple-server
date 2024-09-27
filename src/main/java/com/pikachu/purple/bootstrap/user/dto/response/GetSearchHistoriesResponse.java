package com.pikachu.purple.bootstrap.user.dto.response;

import com.pikachu.purple.application.history.common.dto.SearchHistoryDTO;
import java.util.List;

public record GetSearchHistoriesResponse(List<SearchHistoryDTO> searchHistories) {}
