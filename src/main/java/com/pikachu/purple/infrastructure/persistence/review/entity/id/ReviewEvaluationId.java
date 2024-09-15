package com.pikachu.purple.infrastructure.persistence.review.entity.id;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

@AllArgsConstructor
@EqualsAndHashCode
public class ReviewEvaluationId implements Serializable {

    private Long reviewJpaEntity;
    private String fieldCode;
    private String optionCode;

}
