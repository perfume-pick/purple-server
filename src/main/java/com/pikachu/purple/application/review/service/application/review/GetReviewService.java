package com.pikachu.purple.application.review.service.application.review;

import com.pikachu.purple.application.review.common.dto.ReviewByUserDTO;
import com.pikachu.purple.application.review.common.dto.ReviewEvaluationFieldDTO;
import com.pikachu.purple.application.review.common.dto.ReviewEvaluationOptionDTO;
import com.pikachu.purple.application.review.port.in.review.GetReviewUseCase;
import com.pikachu.purple.application.review.port.out.ReviewRepository;
import com.pikachu.purple.application.review.service.domain.StarRatingDomainService;
import com.pikachu.purple.domain.review.Mood;
import com.pikachu.purple.domain.review.Review;
import com.pikachu.purple.domain.review.StarRating;
import com.pikachu.purple.domain.review.enums.ReviewType;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
class GetReviewService implements GetReviewUseCase {

    private final ReviewRepository reviewRepository;
    private final StarRatingDomainService starRatingDomainService;

    @Transactional
    @Override
    public Result find(Long userId, Long perfumeId) {
        Review review = reviewRepository.findWithPerfumeAndReviewEvaluationAndMood(
            userId,
            perfumeId
        );

        StarRating starRating = starRatingDomainService.findByUserIdAndPerfumeId(
            userId,
            perfumeId
        );

        ReviewByUserDTO reviewByUserDTO = null;
        if(review != null) {
            if (review.getType() == ReviewType.SIMPLE) {
                reviewByUserDTO = ReviewByUserDTO.from(review);
            } else {
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

                reviewByUserDTO = ReviewByUserDTO.of(
                    review,
                    reviewEvaluation,
                    moodNames
                );
            }
        }

        if(review == null && starRating != null) {
            reviewByUserDTO = ReviewByUserDTO.from(starRating);
        }

        if(review == null && starRating == null) {
            reviewByUserDTO = ReviewByUserDTO.empty();
        }

        return new Result(reviewByUserDTO);
    }

}
