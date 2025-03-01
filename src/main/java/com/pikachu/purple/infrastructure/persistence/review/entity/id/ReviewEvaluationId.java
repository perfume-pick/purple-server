package com.pikachu.purple.infrastructure.persistence.review.entity.id;

import java.io.Serializable;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode
@NoArgsConstructor
public class ReviewEvaluationId implements Serializable {

    private Long reviewId;
    private String fieldCode;
    private String optionCode;

}
