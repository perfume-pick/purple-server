package com.pikachu.purple.infrastructure.persistence.statistic.entity.id;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class EvaluationStatisticId implements Serializable {
    private Long perfumeId;
    private String fieldCode;
    private String optionCode;
}
