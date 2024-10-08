package com.pikachu.purple.application.history.common.dto;

public record SearchHistoryDTO(
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
