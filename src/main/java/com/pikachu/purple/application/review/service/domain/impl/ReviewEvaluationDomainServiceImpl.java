package com.pikachu.purple.application.review.service.domain.impl;

import com.pikachu.purple.application.review.port.out.ReviewEvaluationRepository;
import com.pikachu.purple.application.review.service.domain.ReviewEvaluationDomainService;
import com.pikachu.purple.domain.review.ReviewEvaluation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewEvaluationDomainServiceImpl implements ReviewEvaluationDomainService {

    private final ReviewEvaluationRepository reviewEvaluationRepository;


    @Override
    public void create(
        Long reviewId,
        ReviewEvaluation reviewEvaluation
    ) {
        reviewEvaluationRepository.create(
            reviewId,
            reviewEvaluation
        );
    }

    @Override
    public ReviewEvaluation findByReviewId(Long reviewId) {
        return reviewEvaluationRepository.findByReviewId(reviewId);
    }

    @Override
    public void deleteAll(Long reviewId) {
        reviewEvaluationRepository.deleteAll(reviewId);
    }

    @Override
    public void update(
        Long reviewId,
        ReviewEvaluation reviewEvaluation
    ) {
       reviewEvaluationRepository.update(
           reviewId,
           reviewEvaluation
       );
    }

}
