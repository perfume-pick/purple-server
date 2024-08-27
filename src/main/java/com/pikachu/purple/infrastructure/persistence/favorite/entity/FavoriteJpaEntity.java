package com.pikachu.purple.infrastructure.persistence.favorite.entity;

import com.pikachu.purple.domain.favorite.Favorite;
import com.pikachu.purple.infrastructure.persistence.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Getter
@Entity
@Table(name = "favorite")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SQLDelete(sql = "UPDATE favorite SET is_active = false WHERE favorite_id = ?")
@SQLRestriction("is_active = true")
public class FavoriteJpaEntity extends BaseEntity {

    @Id
    @Column(name = "favorite_id")
    private Long favoriteId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "perfume_id")
    private Long perfumeId;

    @Builder
    public FavoriteJpaEntity(
        Long favoriteId,
        Long userId,
        Long perfumeId
    ) {
        this.favoriteId = favoriteId;
        this.userId = userId;
        this.perfumeId = perfumeId;
    }

    public static FavoriteJpaEntity toJpaEntity(Favorite favorite) {
        return FavoriteJpaEntity.builder()
            .favoriteId(favorite.getFavoriteId())
            .userId(favorite.getUserId())
            .perfumeId(favorite.getPerfumeId())
            .build();
    }

}
