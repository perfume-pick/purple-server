package com.pikachu.purple.infrastructure.persistence.statistic.entity.id;

import java.io.Serializable;
import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@AllArgsConstructor
@EqualsAndHashCode
public class EvaluationStatisticId implements Serializable {

    private String statisticsDate;
    private Long perfumeJpaEntity;
    private String fieldCode;
    private String optionCode;

}
