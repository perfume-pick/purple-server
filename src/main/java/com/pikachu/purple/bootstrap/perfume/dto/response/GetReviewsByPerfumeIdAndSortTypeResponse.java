package com.pikachu.purple.bootstrap.perfume.dto.response;

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
public class GetReviewsByPerfumeIdAndSortTypeResponse {

    private final List<ReviewDTO> reviews;

    private GetReviewsByPerfumeIdAndSortTypeResponse(List<ReviewDTO> reviews) {
        this.reviews = reviews;
    }

    public static GetReviewsByPerfumeIdAndSortTypeResponse of(Long currentUserId, GetReviewsUseCase.Result result) {
        List<ReviewDTO> reviewDTOs = result.reviews().stream()
            .map(review -> {
                List<ReviewEvaluationFieldDTO> reviewEvaluation = review.getEvaluation()
                    .getFields(review.getId()).stream()
                    .map(fieldType ->
                        ReviewEvaluationFieldDTO.of(
                            fieldType,
                            review.getEvaluation().getOptions(review.getId(), fieldType).stream()
                                .map(ReviewEvaluationOptionDTO::of)
                                .toList()
                        )
                    ).toList();

                List<String> moodNames = review.getMoods().stream()
                    .map(Mood::getName)
                    .toList();

                return ReviewDTO.of(
                    currentUserId,
                    review,
                    reviewEvaluation,
                    moodNames
                );
            })
            .toList();

        return new GetReviewsByPerfumeIdAndSortTypeResponse(reviewDTOs);
    }

    record ReviewDTO(
        String reviewId,
        String nickname,
        String imageUrl,
        String date,
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
                DateUtil.toString(review.getUpdatedAt()),
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
