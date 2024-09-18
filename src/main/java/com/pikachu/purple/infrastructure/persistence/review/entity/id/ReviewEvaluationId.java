package com.pikachu.purple.infrastructure.persistence.review.entity.id;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode
@NoArgsConstructor
public class ReviewEvaluationId implements Serializable {

    private Long reviewJpaEntity;
    private String fieldCode;
    private String optionCode;

}
