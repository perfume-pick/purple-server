package com.pikachu.purple.infrastructure.persistence.statistic.entity.id;

import java.io.Serializable;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode
@NoArgsConstructor
public class EvaluationStatisticId implements Serializable {
    private Long perfumeJpaEntity;
    private String fieldCode;
    private String optionCode;

}
