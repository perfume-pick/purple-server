package com.pikachu.purple.infrastructure.persistence.statistic.entity.id;

import java.io.Serializable;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode
@NoArgsConstructor
public class StarRatingStatisticId implements Serializable {

//    private String statisticsDate;
    private Long perfumeJpaEntity;
    private int score;

}
