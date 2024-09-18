package com.pikachu.purple.infrastructure.persistence.perfume.entity.id;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode
@NoArgsConstructor
public class FragranticaEvaluationId implements Serializable {

    private Long perfumeJpaEntity;
    private String fieldCode;
    private String optionCode;

}
