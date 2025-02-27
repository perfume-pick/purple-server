package com.pikachu.purple.application.review.service.review;

import com.pikachu.purple.application.review.common.dto.ReviewByUserDTO;
import com.pikachu.purple.application.review.common.dto.ReviewEvaluationFieldDTO;
import com.pikachu.purple.application.review.common.dto.ReviewEvaluationOptionDTO;
import com.pikachu.purple.application.review.port.in.review.GetReviewUseCase;
import com.pikachu.purple.application.review.port.out.ReviewRepository;
import com.pikachu.purple.application.review.port.out.StarRatingRepository;
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
    private final StarRatingRepository starRatingRepository;

    @Transactional
    @Override
    public Result find(Long userId, Long perfumeId) {
        Review review = reviewRepository.findWithPerfumeAndReviewEvaluationAndMood(
            userId,
            perfumeId
        );

        StarRating starRating = starRatingRepository.findByUserIdAndPerfumeId(
            userId,
            perfumeId
        );

        if(review == null && starRating != null) {
            review = Review.builder()
                .type(ReviewType.ONBOARDING)
                .starRating(starRating)
                .build();
        }

        return new Result(review);
    }

}
