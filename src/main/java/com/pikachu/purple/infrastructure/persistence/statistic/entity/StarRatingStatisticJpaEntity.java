package com.pikachu.purple.infrastructure.persistence.statistic.entity;

import com.pikachu.purple.domain.statistic.StarRatingStatistic;
import com.pikachu.purple.infrastructure.persistence.common.BaseEntity;
import com.pikachu.purple.infrastructure.persistence.perfume.entity.PerfumeJpaEntity;
import com.pikachu.purple.infrastructure.persistence.statistic.entity.id.StarRatingStatisticId;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Builder
@Table(name = "star_rating_statistic")
@IdClass(StarRatingStatisticId.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class StarRatingStatisticJpaEntity extends BaseEntity {

    @Id
    @Column(name = "perfume_id")
    private Long perfumeId;

    @Id
    @Column(name = "score")
    private int score;

    @Column(name = "votes")
    private int votes;

    public void increase() {
        this.votes++;
    }

    public void decrease() {
        this.votes--;
    }

    public static StarRatingStatistic toDomain(StarRatingStatisticJpaEntity jpaEntity) {
        return StarRatingStatistic.builder()
            .perfume(PerfumeJpaEntity.toDummy(jpaEntity.getPerfumeId()))
            .score(jpaEntity.getScore())
            .votes(jpaEntity.getVotes())
            .build();
    }

}
