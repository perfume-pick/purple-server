package com.pikachu.purple.domain.review;

import com.pikachu.purple.domain.perfume.Perfume;
import com.pikachu.purple.domain.review.enums.ReviewType;
import com.pikachu.purple.domain.user.User;
import java.time.Instant;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review {

    private Long id;
    private String content;
    private ReviewType type;
    private Instant updatedAt;
    private int likeCount;

    @Setter
    private StarRating starRating;
    @Setter
    private User user;
    @Setter
    private Perfume perfume;
    @Setter
    private ReviewEvaluation evaluation;
    @Setter
    private List<Mood> moods;
    @Setter
    private boolean isComplained;
    @Setter
    private boolean isLiked;

    public Review(
        Long id,
        String content,
        ReviewType type,
        Instant updatedAt,
        int likeCount
    ) {
        this.id = id;
        this.content = content;
        this.type = type;
        this.updatedAt = updatedAt;
        this.likeCount = likeCount;
    }

}
