package com.pikachu.purple.application.review.common.dto;

import com.pikachu.purple.domain.review.Review;
import java.time.Instant;
import java.util.List;

public record ReviewDTO(
    Long reviewId,
    String nickname,
    String imageUrl,
    Instant date,
    int score,
    String content,
    List<ReviewEvaluationFieldDTO> perfumeEvaluation,
    List<String> moodNames,
    boolean isComplained,
    boolean isLiked,
    int likeCount
) {

    public static ReviewDTO of(
        Review review,
        List<ReviewEvaluationFieldDTO> perfumeEvaluation,
        List<String> moodNames
    ) {
        return new ReviewDTO(
            review.getId(),
            review.getUser().getNickname(),
            review.getUser().getImageUrl(),
            review.getDate(),
            review.getStarRating().getScore(),
            review.getContent(),
            perfumeEvaluation,
            moodNames,
            false, //TODO
            false, //TODO
            review.getLikeCount()
        );
    }

}
