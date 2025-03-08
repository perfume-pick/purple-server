package com.pikachu.purple.application.review.service.review;

import com.pikachu.purple.application.review.port.in.mood.GetMoodsUseCase;
import com.pikachu.purple.application.review.port.in.review.GetReviewUseCase;
import com.pikachu.purple.application.review.port.in.reviewevaluation.GetReviewEvaluationUseCase;
import com.pikachu.purple.application.review.port.in.starrating.GetStarRatingUseCase;
import com.pikachu.purple.application.review.port.out.ReviewRepository;
import com.pikachu.purple.domain.review.Mood;
import com.pikachu.purple.domain.review.Review;
import com.pikachu.purple.domain.review.ReviewEvaluation;
import com.pikachu.purple.domain.review.StarRating;
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

    @Transactional
    @Override
    public Result findByUserIdAndPerfumeId(
        Long userId,
        Long perfumeId
    ) {
        Review review = reviewRepository.findUserIdAndPerfumeId(
            userId,
            perfumeId
        );

        return new Result(review);
    }

    @Transactional
    @Override
    public Result findByUserIdAndPerfumeIdWithStarRatingAndEvaluationAndMoods(
        Long userId,
        Long perfumeId
    ) {
        Review review = reviewRepository.findUserIdAndPerfumeId(
            userId,
            perfumeId
        );

        ReviewEvaluation reviewEvaluation = getReviewEvaluationUseCase.find(review)
            .reviewEvaluation();
        review.setEvaluation(reviewEvaluation);

        List<Mood> moods = getMoodsUseCase.findAll(review).moods();
        review.setMoods(moods);

        StarRating starRating = getStarRatingUseCase.findByStarRatingId(review.getStarRating().getId())
            .starRating();
        review.setStarRating(starRating);

        return new Result(review);
    }

}
