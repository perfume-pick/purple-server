package com.pikachu.purple.infrastructure.persistence.review.entity.id;

import java.io.Serializable;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode
@NoArgsConstructor
public class ReviewMoodId implements Serializable {

    private Long reviewJpaEntity;
    private String moodJpaEntity;

}