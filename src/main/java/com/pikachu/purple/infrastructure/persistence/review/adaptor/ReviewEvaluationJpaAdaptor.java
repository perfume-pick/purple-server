package com.pikachu.purple.infrastructure.persistence.review.adaptor;

import com.pikachu.purple.application.review.port.out.ReviewEvaluationRepository;
import com.pikachu.purple.bootstrap.common.exception.BusinessException;
import com.pikachu.purple.bootstrap.common.exception.ErrorCode;
import com.pikachu.purple.domain.perfume.Perfume;
import com.pikachu.purple.domain.review.Review;
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
    public void create(
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
    public ReviewEvaluation find() {
        List<ReviewEvaluationJpaEntity> reviewEvaluationJpaEntities =
            reviewEvaluationJpaRepository.findAll(
                Sort.by(
                    Sort.Order.asc("reviewId"),
                    Sort.Order.asc("fieldCode"),
                    Sort.Order.asc("optionCode")
                )
            );

        return ReviewEvaluationJpaEntity.toDomain(reviewEvaluationJpaEntities);
    }

    @Override
    public ReviewEvaluation find(Review review) {
        List<ReviewEvaluationJpaEntity> reviewEvaluationJpaEntities =
            reviewEvaluationJpaRepository.findByReviewIdOrderByFieldCodeAscOptionCodeAsc(review.getId());

        return ReviewEvaluationJpaEntity.toDomain(reviewEvaluationJpaEntities);
    }

    @Override
    public ReviewEvaluation find(Perfume perfume) {
        List<ReviewEvaluationJpaEntity> reviewEvaluationJpaEntities =
            reviewEvaluationJpaRepository.findByPerfumeIdOrderByFieldCodeAscOptionCodeAsc(perfume.getId());

        return ReviewEvaluationJpaEntity.toDomain(reviewEvaluationJpaEntities);
    }

    @Override
    public ReviewEvaluation find(Long reviewId) {
        List<ReviewEvaluationJpaEntity> reviewEvaluationJpaEntities =
            reviewEvaluationJpaRepository.findByReviewIdOrderByFieldCodeAscOptionCodeAsc(reviewId);

        return ReviewEvaluationJpaEntity.toDomain(reviewEvaluationJpaEntities);
    }

    @Override
    public void delete(Long reviewId) {
        List<ReviewEvaluationJpaEntity> reviewEvaluationJpaEntities =
            reviewEvaluationJpaRepository.findByReviewId(reviewId);

        reviewEvaluationJpaRepository.deleteAll(reviewEvaluationJpaEntities);
    }

    @Override
    public void update(
        ReviewEvaluation reviewEvaluation
    ) {
        reviewEvaluation.getReviewIdSet().forEach(this::delete);
        create(reviewEvaluation);
    }

}
