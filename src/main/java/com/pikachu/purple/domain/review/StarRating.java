package com.pikachu.purple.domain.review;

import com.pikachu.purple.domain.perfume.Perfume;
import com.pikachu.purple.domain.user.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class StarRating {

    private User user;
    private Perfume perfume;
    private int score;

}
