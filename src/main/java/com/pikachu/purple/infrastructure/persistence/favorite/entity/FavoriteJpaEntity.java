package com.pikachu.purple.infrastructure.persistence.favorite.entity;

import com.pikachu.purple.infrastructure.persistence.common.BaseEntity;
import com.pikachu.purple.infrastructure.persistence.common.EntityStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "favorite")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FavoriteJpaEntity extends BaseEntity {

    @Id
    @Column(name = "favorite_id")
    private Long favoriteId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "perfume_id")
    private Long perfumeId;

    @Enumerated(EnumType.STRING)
    @Column(name = "entity_status")
    private EntityStatus entityStatus;

}
