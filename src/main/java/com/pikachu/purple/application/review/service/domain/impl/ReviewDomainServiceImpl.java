package com.pikachu.purple.application.review.service.domain.impl;

import com.pikachu.purple.application.review.port.out.ReviewRepository;
import com.pikachu.purple.application.review.service.domain.ReviewDomainService;
import com.pikachu.purple.application.util.IdGenerator;
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
        Long reviewId = IdGenerator.generate();

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
    public List<Review> findAllWithReviewEvaluationAndMoodOrderByCreatedAtDesc(Long perfumeId) {
        return reviewRepository.findAllWithReviewEvaluationAndMoodOrderByCreatedAtDesc(perfumeId);
    }

    @Override
    public List<Review> findAllWithReviewEvaluationAndMoodOrderByScoreDesc(Long perfumeId) {
//        return reviewRepository.findAllWithStarRatingAndReviewEvaluationAndMoodOrderByScoreDesc(perfumeId);
        return null;
    }

    @Override
    public List<Review> findAllWithReviewEvaluationAndMoodOrderByScoreAsc(Long perfumeId) {
//        return reviewRepository.findAllWithStarRatingAndReviewEvaluationAndMoodOrderByScoreAsc(perfumeId);
        return null;
    }

    @Override
    public Review findById(Long reviewId) {
        return reviewRepository.findById(reviewId);
    }

}
