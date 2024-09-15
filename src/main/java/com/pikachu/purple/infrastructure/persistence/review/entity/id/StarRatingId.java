package com.pikachu.purple.infrastructure.persistence.review.entity.id;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

@AllArgsConstructor
@EqualsAndHashCode
public class StarRatingId implements Serializable {

    private Long userJpaEntity;
    private Long perfumeJpaEntity;

}
