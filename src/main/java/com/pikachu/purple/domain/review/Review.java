package com.pikachu.purple.domain.review;

import com.pikachu.purple.domain.review.enums.ReviewType;
import com.pikachu.purple.domain.user.User;
import java.util.Set;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review {

    private Long id;
    private User user;
    private String content;
    private ReviewType type;
    private StarRating starRating;
    private ReviewEvaluation evaluation;
    private Set<Mood> moods;
    private boolean isComplained;
    private boolean isLiked;
    private int likeCount;


    public void updateContent(String content) {
        this.content = content;
    }

}
