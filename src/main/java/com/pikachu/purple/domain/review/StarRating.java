package com.pikachu.purple.domain.review;

import com.pikachu.purple.domain.perfume.Perfume;
import com.pikachu.purple.domain.user.User;
import java.time.Instant;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class StarRating {

    private Long id;
    private int score;
    private Instant updatedAt;

    @Setter
    private User user;
    @Setter
    private Perfume perfume;
    @Setter
    private Review review;

    public StarRating(
        Long id,
        int score,
        Instant updatedAt
    ) {
        this.id = id;
        this.score = score;
        this.updatedAt = updatedAt;
    }

}
