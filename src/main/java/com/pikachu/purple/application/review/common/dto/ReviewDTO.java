package com.pikachu.purple.application.review.common.dto;

import com.pikachu.purple.application.util.IdUtil;
import com.pikachu.purple.domain.review.Review;
import com.pikachu.purple.domain.review.enums.ReviewType;
import java.time.Instant;
import java.util.List;

public record ReviewDTO(
    String reviewId,
    String nickname,
    String imageUrl,
    Instant date,
    ReviewType reviewType,
    int score,
    String content,
    List<ReviewEvaluationFieldDTO> perfumeEvaluation,
    List<String> moodNames,
    boolean isCurrentUserReview,
    boolean isComplained,
    boolean isLiked,
    int likeCount
) {

    public static ReviewDTO of(
        Long currentUserId,
        Review review,
        List<ReviewEvaluationFieldDTO> perfumeEvaluation,
        List<String> moodNames
    ) {
        return new ReviewDTO(
            IdUtil.toString(review.getId()),
            review.getUser().getNickname(),
            review.getUser().getImageUrl(),
            review.getUpdatedAt(),
            review.getType(),
            review.getStarRating().getScore(),
            review.getContent(),
            perfumeEvaluation,
            moodNames,
            currentUserId.equals(review.getUser().getId()),
            review.isComplained(),
            review.isLiked(),
            review.getLikeCount()
        );
    }

}
