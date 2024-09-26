package com.pikachu.purple.application.review.service.domain.impl;

import com.pikachu.purple.application.review.port.out.ReviewRepository;
import com.pikachu.purple.application.review.service.domain.ReviewDomainService;
import com.pikachu.purple.application.util.IdUtil;
import com.pikachu.purple.domain.review.Review;
import com.pikachu.purple.domain.review.enums.ReviewType;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewDomainServiceImpl implements ReviewDomainService {

    private final ReviewRepository reviewRepository;

    @Override
    public Review create(
        Long userId,
        Long perfumeId,
        String content,
        ReviewType reviewType
    ) {
        Long reviewId = IdUtil.generateId();

        Review review = Review.builder()
            .id(reviewId)
            .content(content)
            .type(reviewType)
            .build();

        return reviewRepository.create(userId, perfumeId, review);
    }

    @Override
    public Review updateContent(
        Long reviewId,
        String content
    ) {
        return reviewRepository.updateContent(
            reviewId,
            content
        );
    }

    public void deleteById(Long id) {
        reviewRepository.deleteById(id);
    }

    @Override
    public void createReviewMoods(
        Long reviewId,
        List<String> moodNames
    ) {
        reviewRepository.createReviewMoods(
            reviewId,
            moodNames
        );
    }

    @Override
    public List<Review> findAllWithPerfumeAndReviewEvaluationAndMoodOrderByCreatedAtDesc(Long perfumeId) {
        return reviewRepository.findAllWithPerfumeAndReviewEvaluationAndMoodOrderByCreatedAtDesc(perfumeId);
    }

    @Override
    public List<Review> findAllWithPerfumeAndReviewEvaluationAndMoodOrderByScoreDesc(Long perfumeId) {
        return reviewRepository.findAllWithPerfumeAndReviewEvaluationAndMoodOrderByScoreDesc(perfumeId);
    }

    @Override
    public List<Review> findAllWithPerfumeAndReviewEvaluationAndMoodOrderByScoreAsc(Long perfumeId) {
        return reviewRepository.findAllWithPerfumeAndReviewEvaluationAndMoodOrderByScoreAsc(perfumeId);
    }

    @Override
    public Review findById(Long reviewId) {
        return reviewRepository.findById(reviewId);
    }

}
