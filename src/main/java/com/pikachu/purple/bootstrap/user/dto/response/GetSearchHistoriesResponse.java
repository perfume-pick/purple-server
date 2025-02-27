package com.pikachu.purple.bootstrap.user.dto.response;

import com.pikachu.purple.application.history.port.in.searchhistory.GetSearchHistoriesUseCase;
import java.util.List;
import java.util.stream.IntStream;
import lombok.Getter;

@Getter
public class GetSearchHistoriesResponse{

    private final List<SearchHistoryDTO> searchHistories;

    public GetSearchHistoriesResponse(List<SearchHistoryDTO> searchHistories) {
        this.searchHistories = searchHistories;
    }

    public static GetSearchHistoriesResponse of(GetSearchHistoriesUseCase.Result result) {
        List<SearchHistoryDTO> searchHistories = IntStream.range(0, result.searchHistories().size())
            .mapToObj(i -> SearchHistoryDTO.of(
                i + 1,
                result.searchHistories().get(i).getKeyword()
            ))
            .toList();
        return new GetSearchHistoriesResponse(
            searchHistories
        );
    }

    record SearchHistoryDTO(
        int order,
        String keyword
    ) {

        public static SearchHistoryDTO of(
            int order,
            String keyword
        ) {
            return new SearchHistoryDTO(
                order,
                keyword
            );
        }
    }

}

