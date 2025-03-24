package com.pikachu.purple.infrastructure.persistence.review.entity.id;

import com.pikachu.purple.domain.review.enums.Mood;
import java.io.Serializable;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode
@NoArgsConstructor
public class ReviewMoodId implements Serializable {

    private Long reviewId;
    private Mood mood;

}
