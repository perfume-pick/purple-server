package com.pikachu.purple.domain.user.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserCurrentSearchLog {

    private Long id;
    private String searchName;
    private String searchAt;

    @Builder
    public UserCurrentSearchLog(
        Long id,
        String searchName,
        String searchAt
    ) {
        this.id = id;
        this.searchName = searchName;
        this.searchAt = searchAt;
    }

}
