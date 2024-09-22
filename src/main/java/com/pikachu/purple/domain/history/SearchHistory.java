package com.pikachu.purple.domain.history;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class SearchHistory {

    private Long id;
    private String keyword;

}
