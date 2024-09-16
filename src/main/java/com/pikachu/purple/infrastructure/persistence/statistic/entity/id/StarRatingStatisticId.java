package com.pikachu.purple.infrastructure.persistence.statistic.entity.id;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

@AllArgsConstructor
@EqualsAndHashCode
public class StarRatingStatisticId implements Serializable {

    private String statisticsDate;
    private Long perfumeJpaEntity;
    private int score;

}
