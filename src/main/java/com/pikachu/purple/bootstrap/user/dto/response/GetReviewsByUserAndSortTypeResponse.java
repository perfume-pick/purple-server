package com.pikachu.purple.bootstrap.user.dto.response;

import com.pikachu.purple.application.review.port.in.review.GetReviewsUseCase;
import com.pikachu.purple.application.util.DateUtil;
import com.pikachu.purple.application.util.IdUtil;
import com.pikachu.purple.domain.evaluation.enums.EvaluationFieldType;
import com.pikachu.purple.domain.evaluation.enums.EvaluationOptionType;
import com.pikachu.purple.domain.review.Mood;
import com.pikachu.purple.domain.review.Review;
import com.pikachu.purple.domain.review.enums.ReviewType;
import java.util.List;
import lombok.Getter;

@Getter
public class GetReviewsByUserAndSortTypeResponse {
    private final List<ReviewDTO> reviews;

    private GetReviewsByUserAndSortTypeResponse(List<ReviewDTO> reviews) {
        this.reviews = reviews;
    }

    public static GetReviewsByUserAndSortTypeResponse of(GetReviewsUseCase.Result result) {
        List<ReviewDTO> reviewDTOs = result.reviews().stream()
            .map(review -> {
                List<ReviewEvaluationFieldDTO> reviewEvaluation = review.getEvaluation()
                    .getFields(review.getId()).stream()
                    .map(fieldType ->
                        ReviewEvaluationFieldDTO.of(
                            fieldType,
                            review.getEvaluation().getOptions(review.getId(), fieldType).stream()
                                .map(
                                    ReviewEvaluationOptionDTO::of)
                                .toList()
                        )
                    ).toList();
                List<String> moodNames = review.getMoods().stream()
                    .map(Mood::getName)
                    .toList();

                return ReviewDTO.of(
                    review,
                    reviewEvaluation,
                    moodNames
                );
            })
            .toList();

        return new GetReviewsByUserAndSortTypeResponse(reviewDTOs);
    }

    record ReviewDTO(
        String reviewId,
        String brandName,
        String perfumeId,
        String perfumeName,
        String perfumeImageUrl,
        ReviewType reviewType,
        int score,
        String content,
        List<ReviewEvaluationFieldDTO> perfumeEvaluations,
        List<String> moodNames,
        Boolean isComplained,
        Boolean isLiked,
        Integer likeCount,
        String date
    ) {
        public static ReviewDTO of(
            Review review,
            List<ReviewEvaluationFieldDTO> perfumeEvaluations,
            List<String> moodNames
        ) {
            return new ReviewDTO(
                IdUtil.toString(review.getId()),
                review.getPerfume().getBrand().getKoreanName(),
                IdUtil.toString(review.getPerfume().getId()),
                review.getPerfume().getKoreanName(),
                review.getPerfume().getImageUrl(),
                review.getType(),
                review.getStarRating().getScore(),
                review.getContent(),
                perfumeEvaluations,
                moodNames,
                review.isComplained(),
                review.isLiked(),
                review.getLikeCount(),
                DateUtil.toString(review.getUpdatedAt())
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

        public static ReviewEvaluationOptionDTO of(
            EvaluationOptionType type) {
            return new ReviewEvaluationOptionDTO(type.getName());
        }

    }

}
