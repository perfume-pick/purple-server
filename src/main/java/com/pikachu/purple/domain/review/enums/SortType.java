package com.pikachu.purple.domain.review.enums;

import java.util.EnumSet;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SortType {

    LIKED("좋아요순"),
    LATEST("최신순"),
    STAR_RATING_HIGH("별점높은순"),
    STAR_RATING_LOW("별점낮은순");

    private final String sortTypeStr;

    //TODO null 처리
    public static SortType transByStr(String sortTypeStr) {
        return EnumSet.allOf(SortType.class).stream()
            .filter(e -> e.getSortTypeStr().equals(sortTypeStr))
            .findAny()
            .orElseThrow();
    }

}