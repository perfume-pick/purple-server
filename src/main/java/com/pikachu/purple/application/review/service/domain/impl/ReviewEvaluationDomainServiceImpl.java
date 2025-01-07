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
    public void createAll(ReviewEvaluation reviewEvaluation) {
        reviewEvaluationRepository.createAll(reviewEvaluation);
    }

    @Override
    public ReviewEvaluation findAll() {
        return reviewEvaluationRepository.findAll();
    }

    @Override
    public ReviewEvaluation findAll(Long reviewId) {
        return reviewEvaluationRepository.findAll(reviewId);
    }

    @Override
    public void deleteAll(Long reviewId) {
        reviewEvaluationRepository.deleteAll(reviewId);
    }

    @Override
    public void updateAll(ReviewEvaluation reviewEvaluation) {
       reviewEvaluationRepository.updateAll(reviewEvaluation);
    }

}
