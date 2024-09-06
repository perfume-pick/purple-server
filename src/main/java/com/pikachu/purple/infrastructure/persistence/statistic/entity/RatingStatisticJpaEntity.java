package com.pikachu.purple.infrastructure.persistence.statistic.entity;

import com.pikachu.purple.infrastructure.persistence.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "rating_statistic")
@IdClass(RatingStatisticId.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RatingStatisticJpaEntity extends BaseEntity {

    @Id
    @Column(name = "perfume_id")
    private Long perfumeId;

    @Id
    private int score;

    @Column(name = "votes")
    private int votes;

}
