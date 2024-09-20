package com.pikachu.purple.infrastructure.persistence.statistic.entity;

import com.pikachu.purple.domain.review.StarRating;
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
import java.util.List;
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
    @Column(
        name = "statistics_date",
        columnDefinition = "char(8)",
        nullable = false
    )
    private String statisticsDate;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "perfume_id")
    private PerfumeJpaEntity perfumeJpaEntity;

    @Id
    @Column(name = "score")
    private int score;

    @Column(name = "votes")
    private int votes;

    public static StarRatingStatistic toDomain(StarRatingStatisticJpaEntity jpaEntity) {
        return StarRatingStatistic.builder()
            .score(jpaEntity.getScore())
            .votes(jpaEntity.getVotes())
            .build();
    }

}
