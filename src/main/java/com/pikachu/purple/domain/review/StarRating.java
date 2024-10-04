package com.pikachu.purple.domain.review;

import com.pikachu.purple.domain.perfume.Perfume;
import com.pikachu.purple.domain.user.User;
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

    @Setter
    private User user;
    @Setter
    private Perfume perfume;

    public StarRating(Long id, int score) {
        this.id = id;
        this.score = score;
    }

}
