package com.pikachu.purple.domain.favorite;

import com.pikachu.purple.infrastructure.persistence.common.EntityStatus;
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
    private EntityStatus entityStatus;

    @Builder
    public Favorite(
        Long favoriteId,
        Long userId,
        Long perfumeId,
        EntityStatus entityStatus
    ) {
        this.favoriteId = favoriteId;
        this.userId = userId;
        this.perfumeId = perfumeId;
        this.entityStatus = entityStatus;
    }
}
