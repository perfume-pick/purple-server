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
        ReviewEvaluation reviewEvaluation
    ) {
        reviewEvaluationRepository.create(
            reviewEvaluation
        );
    }

    @Override
    public ReviewEvaluation find(Long reviewId) {
        return reviewEvaluationRepository.find(reviewId);
    }

    @Override
    public void deleteAll(Long reviewId) {
        reviewEvaluationRepository.deleteAll(reviewId);
    }

    @Override
    public void update(
        ReviewEvaluation reviewEvaluation
    ) {
       reviewEvaluationRepository.update(
           reviewEvaluation
       );
    }

}
