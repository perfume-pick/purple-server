package com.pikachu.purple.infrastructure.persistence.review.entity.id;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

@AllArgsConstructor
@EqualsAndHashCode
public class ReviewMoodId implements Serializable {

    private Long reviewJpaEntity;
    private String moodJpaEntity;

}