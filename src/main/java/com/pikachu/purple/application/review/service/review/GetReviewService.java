package com.pikachu.purple.application.review.service.review;

import com.pikachu.purple.application.review.port.in.mood.GetMoodsUseCase;
import com.pikachu.purple.application.review.port.in.review.GetReviewUseCase;
import com.pikachu.purple.application.review.port.in.reviewevaluation.GetReviewEvaluationUseCase;
import com.pikachu.purple.application.review.port.in.starrating.GetStarRatingUseCase;
import com.pikachu.purple.application.review.port.out.ReviewRepository;
import com.pikachu.purple.application.review.port.out.StarRatingRepository;
import com.pikachu.purple.application.statistic.port.in.starratingstatistic.GetStarRatingStatisticsUseCase;
import com.pikachu.purple.domain.review.Mood;
import com.pikachu.purple.domain.review.Review;
import com.pikachu.purple.domain.review.ReviewEvaluation;
import com.pikachu.purple.domain.review.StarRating;
import com.pikachu.purple.domain.review.enums.ReviewType;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
class GetReviewService implements GetReviewUseCase {

    private final GetStarRatingUseCase getStarRatingUseCase;
    private final GetReviewEvaluationUseCase getReviewEvaluationUseCase;
    private final GetMoodsUseCase getMoodsUseCase;

    private final ReviewRepository reviewRepository;
    private final StarRatingRepository starRatingRepository;

    @Transactional
    @Override
    public Result findWithStarRatingAndEvaluationAndMoods(
        Long userId,
        Long perfumeId
    ) {
        Review review = reviewRepository.find(
            userId,
            perfumeId
        );

        ReviewEvaluation reviewEvaluation = getReviewEvaluationUseCase.find(review)
            .reviewEvaluation();
        review.setEvaluation(reviewEvaluation);

        List<Mood> moods = getMoodsUseCase.findAll(review).moods();
        review.setMoods(moods);

        StarRating starRating = starRatingRepository.find(
            userId,
            perfumeId
        );

        if (review == null && starRating != null) {
            review = Review.builder()
                .type(ReviewType.ONBOARDING)
                .starRating(starRating)
                .build();
        } else if (review != null && starRating != null) {
            review.setStarRating(starRating);
        }

        return new Result(review);
    }

}
