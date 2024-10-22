package com.pikachu.purple.domain.review;

import com.pikachu.purple.domain.user.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Like {

    private User user;
    private Review review;

    public Like(
        User user,
        Review review
    ) {
        this.user = user;
        this.review = review;
    }

}
