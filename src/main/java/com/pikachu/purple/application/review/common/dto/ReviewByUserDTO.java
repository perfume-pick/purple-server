package com.pikachu.purple.application.review.common.dto;

import com.pikachu.purple.application.util.IdUtil;
import com.pikachu.purple.domain.review.Review;
import com.pikachu.purple.domain.review.enums.ReviewType;
import java.util.List;

public record ReviewByUserDTO(
    String reviewId,
    ReviewType reviewType,
    int score,
    String content,
    List<ReviewEvaluationFieldDTO> perfumeEvaluation,
    List<String> moodNames
) {

    public static ReviewByUserDTO from(Review review) {
        return new ReviewByUserDTO(
            IdUtil.toString(review.getId()),
            review.getType(),
            review.getStarRating().getScore(),
            review.getContent(),
            null,
            null
        );
    }

    public static ReviewByUserDTO of(
        Review review,
        List<ReviewEvaluationFieldDTO> perfumeEvaluation,
        List<String> moodNames
    ) {
        return new ReviewByUserDTO(
            IdUtil.toString(review.getId()),
            review.getType(),
            review.getStarRating().getScore(),
            review.getContent(),
            perfumeEvaluation,
            moodNames
        );
    }

    public static ReviewByUserDTO empty() {
        return new ReviewByUserDTO(
            null,
            null,
            0,
            null,
            List.of(),
            List.of()
        );
    }

}
