package com.pikachu.purple.infrastructure.persistence.statistic.entity;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

@AllArgsConstructor
@EqualsAndHashCode
public class EvaluationStatisticId implements Serializable {

    private Long perfumeId;
    private String fieldCode;
    private String optionCode;

}
