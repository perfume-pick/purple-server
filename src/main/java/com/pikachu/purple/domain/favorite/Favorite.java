package com.pikachu.purple.domain.favorite;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Favorite {

    private Long favoriteId;
    private Long userId;
    private Long perfumeId;

    @Builder
    public Favorite(
        Long favoriteId,
        Long userId,
        Long perfumeId
    ) {
        this.favoriteId = favoriteId;
        this.userId = userId;
        this.perfumeId = perfumeId;
    }

}
