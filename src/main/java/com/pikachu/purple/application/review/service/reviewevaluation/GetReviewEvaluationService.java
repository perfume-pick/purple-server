package com.pikachu.purple.application.review.service.reviewevaluation;

import com.pikachu.purple.application.review.port.in.reviewevaluation.GetReviewEvaluationUseCase;
import com.pikachu.purple.application.review.port.out.ReviewEvaluationRepository;
import com.pikachu.purple.domain.perfume.Perfume;
import com.pikachu.purple.domain.review.Review;
import com.pikachu.purple.domain.review.ReviewEvaluation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetReviewEvaluationService implements GetReviewEvaluationUseCase {

    private final ReviewEvaluationRepository reviewEvaluationRepository;


    @Override
    public Result find() {
        ReviewEvaluation reviewEvaluation = reviewEvaluationRepository.find();

        return new Result(reviewEvaluation);
    }

    @Override
    public Result find(Review review) {
        ReviewEvaluation reviewEvaluation = reviewEvaluationRepository.find(review);

        return new Result(reviewEvaluation);
    }

    @Override
    public Result find(Perfume perfume) {
        ReviewEvaluation reviewEvaluation = reviewEvaluationRepository.find(perfume);

        return new Result(reviewEvaluation);
    }

}
