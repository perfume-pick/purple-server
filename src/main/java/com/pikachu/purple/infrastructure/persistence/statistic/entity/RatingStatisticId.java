package com.pikachu.purple.infrastructure.persistence.statistic.entity;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

@AllArgsConstructor
@EqualsAndHashCode
public class RatingStatisticId implements Serializable {

    private Long perfumeId;
    private int score;

}
