package com.pikachu.purple.bootstrap.user.dto.response;

import com.pikachu.purple.application.review.common.dto.ReviewEvaluationFieldDTO;
import com.pikachu.purple.application.review.port.in.review.GetReviewUseCase.Result;
import com.pikachu.purple.application.util.IdUtil;
import com.pikachu.purple.domain.evaluation.enums.EvaluationFieldType;
import com.pikachu.purple.domain.evaluation.enums.EvaluationOptionType;
import com.pikachu.purple.domain.review.Mood;
import com.pikachu.purple.domain.review.Review;
import com.pikachu.purple.domain.review.StarRating;
import com.pikachu.purple.domain.review.enums.ReviewType;
import java.util.List;
import lombok.Getter;

@Getter
public class GetReviewByPerfumeIdAndUserResponse {

    private final ReviewDTO review;

    private GetReviewByPerfumeIdAndUserResponse(ReviewDTO review) {
        this.review = review;
    }

    public static GetReviewByPerfumeIdAndUserResponse of(Result result) {
        Review review = result.review();
        ReviewDTO reviewDTO = null;
        if (review != null) {
            if (review.getType() == ReviewType.SIMPLE) {
                reviewDTO = ReviewDTO.from(review);
            } else if (review.getType() == ReviewType.DETAIL) {
                List<ReviewEvaluationDTO> reviewEvaluation = review.getEvaluation()
                    .getFields(review.getId()).stream()
                    .map(fieldType ->
                        ReviewEvaluationDTO.of(
                            fieldType,
                            review.getEvaluation().getOptions(review.getId(), fieldType).stream()
                                .map(EvaluationOptionType::getName
                                    )
                                .toList()
                        )
                    ).toList();

                List<String> moodNames = review.getMoods().stream()
                    .map(Mood::getName)
                    .toList();

                reviewDTO = ReviewDTO.of(
                    review,
                    reviewEvaluation,
                    moodNames
                );
            } else if (review.getType() == ReviewType.ONBOARDING) {
                reviewDTO = ReviewDTO.from(review.getStarRating());
            }
        } else {
            reviewDTO = ReviewDTO.empty();
        }
        return new GetReviewByPerfumeIdAndUserResponse(reviewDTO);
    }

    record ReviewDTO(
        String reviewId,
        ReviewType reviewType,
        int score,
        String content,
        List<ReviewEvaluationDTO> perfumeEvaluation,
        List<String> moodNames
    ) {
        public static ReviewDTO from(Review review) {
            return new ReviewDTO(
                IdUtil.toString(review.getId()),
                review.getType(),
                review.getStarRating().getScore(),
                review.getContent(),
                null,
                null
            );
        }

        public static ReviewDTO from(StarRating starRating) {
            return new ReviewDTO(
                null,
                ReviewType.ONBOARDING,
                starRating.getScore(),
                null,
                null,
                null
            );
        }

        public static ReviewDTO of(
            Review review,
            List<ReviewEvaluationDTO> perfumeEvaluation,
            List<String> moodNames
        ) {
            return new ReviewDTO(
                IdUtil.toString(review.getId()),
                review.getType(),
                review.getStarRating().getScore(),
                review.getContent(),
                perfumeEvaluation,
                moodNames
            );
        }

        public static ReviewDTO empty() {
            return new ReviewDTO(
                null,
                null,
                0,
                null,
                List.of(),
                List.of()
            );
        }
    }

    record ReviewEvaluationDTO(
        String fieldName,
        List<String> options
    ) {
        public static ReviewEvaluationDTO of(
            EvaluationFieldType fieldType,
            List<String> options) {
            return new ReviewEvaluationDTO(
                fieldType.getName(),
                options
            );
        }
    }

}
