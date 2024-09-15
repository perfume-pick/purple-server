package com.pikachu.purple.infrastructure.persistence.statistic.entity.id;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

@AllArgsConstructor
@EqualsAndHashCode
public class EvaluationStatisticId implements Serializable {

    private Long perfumeJpaEntity;
    private String fieldCode;
    private String optionCode;

}
