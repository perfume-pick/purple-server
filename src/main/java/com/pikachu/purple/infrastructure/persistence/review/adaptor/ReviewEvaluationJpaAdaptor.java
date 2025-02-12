package com.pikachu.purple.infrastructure.persistence.review.adaptor;

import com.pikachu.purple.application.review.port.out.ReviewEvaluationRepository;
import com.pikachu.purple.bootstrap.common.exception.BusinessException;
import com.pikachu.purple.bootstrap.common.exception.ErrorCode;
import com.pikachu.purple.domain.review.ReviewEvaluation;
import com.pikachu.purple.infrastructure.persistence.review.entity.ReviewEvaluationJpaEntity;
import com.pikachu.purple.infrastructure.persistence.review.entity.ReviewJpaEntity;
import com.pikachu.purple.infrastructure.persistence.review.repository.ReviewEvaluationJpaRepository;
import com.pikachu.purple.infrastructure.persistence.review.repository.ReviewJpaRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReviewEvaluationJpaAdaptor implements ReviewEvaluationRepository {

    private final ReviewJpaRepository reviewJpaRepository;
    private final ReviewEvaluationJpaRepository reviewEvaluationJpaRepository;

    @Override
    public void createAll(
        ReviewEvaluation reviewEvaluation
    ) {
        List<ReviewEvaluationJpaEntity> reviewEvaluationJpaEntities = new ArrayList<>();

        reviewEvaluation.getReviewIdSet().forEach(
            reviewId -> {
                ReviewJpaEntity reviewJpaEntity = reviewJpaRepository.findById(reviewId)
                    .orElseThrow(() -> new BusinessException(ErrorCode.REVIEW_NOT_FOUND));

                reviewEvaluationJpaEntities.addAll(
                    ReviewEvaluationJpaEntity.toJpaEntityList(reviewJpaEntity, reviewEvaluation));
            });

        reviewEvaluationJpaRepository.saveAll(reviewEvaluationJpaEntities);
    }

    @Override
    public ReviewEvaluation findAll() {
        List<ReviewEvaluationJpaEntity> reviewEvaluationJpaEntities =
            reviewEvaluationJpaRepository.findAll(
                Sort.by(
                    Sort.Order.asc("reviewJpaEntity.id"),
                    Sort.Order.asc("fieldCode"),
                    Sort.Order.asc("optionCode")
                )
            );

        return ReviewEvaluationJpaEntity.toDomain(reviewEvaluationJpaEntities);
    }

    @Override
    public ReviewEvaluation findAll(Long reviewId) {
        List<ReviewEvaluationJpaEntity> reviewEvaluationJpaEntities =
            reviewEvaluationJpaRepository.findByReviewId(reviewId);

        return ReviewEvaluationJpaEntity.toDomain(reviewEvaluationJpaEntities);
    }

    @Override
    public void deleteAll(Long reviewId) {
        List<ReviewEvaluationJpaEntity> reviewEvaluationJpaEntities =
            reviewEvaluationJpaRepository.findByReviewId(reviewId);

        reviewEvaluationJpaRepository.deleteAll(reviewEvaluationJpaEntities);
    }

    @Override
    public void updateAll(
        ReviewEvaluation reviewEvaluation
    ) {
        reviewEvaluation.getReviewIdSet().forEach(this::deleteAll);
        createAll(reviewEvaluation);
    }

}
