package com.pikachu.purple.infrastructure.persistence.review.adaptor;

import com.pikachu.purple.application.rating.port.out.StarRatingRepository;
import com.pikachu.purple.application.review.port.out.ReviewEvaluationRepository;
import com.pikachu.purple.bootstrap.common.exception.BusinessException;
import com.pikachu.purple.bootstrap.common.exception.ErrorCode;
import com.pikachu.purple.domain.evaluation.EvaluationField;
import com.pikachu.purple.domain.evaluation.EvaluationOption;
import com.pikachu.purple.domain.evaluation.enums.EvaluationFieldType;
import com.pikachu.purple.domain.evaluation.enums.EvaluationOptionType;
import com.pikachu.purple.domain.review.ReviewEvaluation;
import com.pikachu.purple.domain.review.StarRating;
import com.pikachu.purple.infrastructure.persistence.perfume.entity.PerfumeJpaEntity;
import com.pikachu.purple.infrastructure.persistence.perfume.repository.PerfumeJpaRepository;
import com.pikachu.purple.infrastructure.persistence.review.entity.ReviewEvaluationJpaEntity;
import com.pikachu.purple.infrastructure.persistence.review.entity.ReviewJpaEntity;
import com.pikachu.purple.infrastructure.persistence.review.entity.StarRatingJpaEntity;
import com.pikachu.purple.infrastructure.persistence.review.repository.ReviewEvaluationJpaRepository;
import com.pikachu.purple.infrastructure.persistence.review.repository.ReviewJpaRepository;
import com.pikachu.purple.infrastructure.persistence.review.repository.StarRatingJpaRepository;
import com.pikachu.purple.infrastructure.persistence.user.entity.UserJpaEntity;
import com.pikachu.purple.infrastructure.persistence.user.repository.UserJpaRepository;
import java.util.ArrayList;
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
}
