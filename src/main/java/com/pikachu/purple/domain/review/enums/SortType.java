package com.pikachu.purple.domain.review.enums;

import static com.pikachu.purple.bootstrap.common.exception.BusinessException.SortTypeNotFoundException;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SortType {

    LIKED, LATEST, STAR_RATING_HIGH, STAR_RATING_LOW;

    public static SortType transByStr(String sortTypeStr) {
        try{
            return SortType.valueOf(sortTypeStr.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw SortTypeNotFoundException;
        }
    }

}
