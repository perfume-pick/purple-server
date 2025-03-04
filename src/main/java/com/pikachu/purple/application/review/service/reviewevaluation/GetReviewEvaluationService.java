package com.pikachu.purple.application.review.service.reviewevaluation;

import com.pikachu.purple.application.review.port.in.reviewevaluation.GetReviewEvaluationUseCase;
import com.pikachu.purple.application.review.port.out.ReviewEvaluationRepository;
import com.pikachu.purple.domain.perfume.Perfume;
import com.pikachu.purple.domain.review.Review;
import com.pikachu.purple.domain.review.ReviewEvaluation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GetReviewEvaluationService implements GetReviewEvaluationUseCase {

    private final ReviewEvaluationRepository reviewEvaluationRepository;

    @Transactional
    @Override
    public Result find() {
        ReviewEvaluation reviewEvaluation = reviewEvaluationRepository.find();

        return new Result(reviewEvaluation);
    }

    @Transactional
    @Override
    public Result find(Review review) {
        ReviewEvaluation reviewEvaluation = reviewEvaluationRepository.findByReviewId(review.getId());

        return new Result(reviewEvaluation);
    }

    @Transactional
    @Override
    public Result find(Perfume perfume) {
        ReviewEvaluation reviewEvaluation = reviewEvaluationRepository.findByPerfumeId(perfume.getId());

        return new Result(reviewEvaluation);
    }

}
