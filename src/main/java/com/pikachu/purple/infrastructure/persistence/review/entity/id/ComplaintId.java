package com.pikachu.purple.infrastructure.persistence.review.entity.id;

import java.io.Serializable;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode
@NoArgsConstructor
public class ComplaintId implements Serializable {

    private Long userJpaEntity;
    private Long reviewJpaEntity;

}
