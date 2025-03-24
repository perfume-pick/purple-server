package com.pikachu.purple.bootstrap.user.dto.response;

import com.pikachu.purple.application.review.port.in.review.GetReviewUseCase.Result;
import com.pikachu.purple.application.util.IdUtil;
import com.pikachu.purple.domain.evaluation.enums.EvaluationFieldType;
import com.pikachu.purple.domain.evaluation.enums.EvaluationOptionType;
import com.pikachu.purple.domain.review.enums.Mood;
import com.pikachu.purple.domain.review.Review;
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
            if (review.getType() == ReviewType.DETAIL) {
                List<ReviewEvaluationFieldDTO> reviewEvaluation = review.getEvaluation()
                    .getFields(review.getId()).stream()
                    .map(fieldType ->
                        ReviewEvaluationFieldDTO.of(
                            fieldType,
                            review.getEvaluation().getOptions(review.getId(), fieldType).stream()
                                .map(ReviewEvaluationOptionDTO::of
                                    )
                                .toList()
                        )
                    ).toList();

                List<String> moodNames = review.getMoods().stream()
                    .map(Mood::getKoreanName)
                    .toList();

                reviewDTO = ReviewDTO.of(
                    review,
                    reviewEvaluation,
                    moodNames
                );
            } else {
                reviewDTO = ReviewDTO.from(review);
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
        List<ReviewEvaluationFieldDTO> perfumeEvaluation,
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

        public static ReviewDTO of(
            Review review,
            List<ReviewEvaluationFieldDTO> perfumeEvaluation,
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

    public record ReviewEvaluationFieldDTO(
        String fieldName,
        List<ReviewEvaluationOptionDTO> options
    ) {

        public static ReviewEvaluationFieldDTO of(
            EvaluationFieldType fieldType,
            List<ReviewEvaluationOptionDTO> options) {
            return new ReviewEvaluationFieldDTO(
                fieldType.getName(),
                options
            );
        }

    }

    public record ReviewEvaluationOptionDTO(String optionName) {

        public static ReviewEvaluationOptionDTO of(EvaluationOptionType type) {
            return new ReviewEvaluationOptionDTO(type.getName());
        }

    }

}
