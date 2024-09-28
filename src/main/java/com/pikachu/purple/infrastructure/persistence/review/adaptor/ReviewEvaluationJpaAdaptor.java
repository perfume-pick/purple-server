package com.pikachu.purple.infrastructure.persistence.review.adaptor;

import com.pikachu.purple.application.review.port.out.ReviewEvaluationRepository;
import com.pikachu.purple.bootstrap.common.exception.BusinessException;
import com.pikachu.purple.bootstrap.common.exception.ErrorCode;
import com.pikachu.purple.domain.review.ReviewEvaluation;
import com.pikachu.purple.infrastructure.persistence.review.entity.ReviewEvaluationJpaEntity;
import com.pikachu.purple.infrastructure.persistence.review.entity.ReviewJpaEntity;
import com.pikachu.purple.infrastructure.persistence.review.repository.ReviewEvaluationJpaRepository;
import com.pikachu.purple.infrastructure.persistence.review.repository.ReviewJpaRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReviewEvaluationJpaAdaptor implements ReviewEvaluationRepository {

    private final ReviewJpaRepository reviewJpaRepository;
    private final ReviewEvaluationJpaRepository reviewEvaluationJpaRepository;

    @Override
    public void create(
        Long reviewId,
        ReviewEvaluation reviewEvaluation
    ) {
        ReviewJpaEntity reviewJpaEntity = reviewJpaRepository.findById(reviewId)
            .orElseThrow(() -> new BusinessException(ErrorCode.REVIEW_NOT_FOUND));

        List<ReviewEvaluationJpaEntity> reviewEvaluationJpaEntities =
            reviewEvaluation.getFields().stream()
                .flatMap(field -> field.getOptions().stream()
                    .map(option -> ReviewEvaluationJpaEntity.builder()
                        .reviewJpaEntity(reviewJpaEntity)
                        .fieldCode(field.getType().getCode())
                        .optionCode(option.getType().getCode())
                        .build()))
                .toList();

        reviewEvaluationJpaRepository.saveAll(reviewEvaluationJpaEntities);
    }

    @Override
    public ReviewEvaluation findByReviewId(Long reviewId) {

        List<ReviewEvaluationJpaEntity> reviewEvaluationJpaEntities =
            reviewEvaluationJpaRepository.findByReviewId(reviewId);
        return ReviewEvaluationJpaEntity.toDomain(reviewEvaluationJpaEntities);
    }

}
