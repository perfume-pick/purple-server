package com.pikachu.purple.application.review.service.domain.impl;

import com.pikachu.purple.application.review.port.out.ReviewRepository;
import com.pikachu.purple.application.review.service.domain.ReviewDomainService;
import com.pikachu.purple.domain.review.Review;
import com.pikachu.purple.infrastructure.persistence.common.ReviewType;
import java.util.List;
import java.util.stream.IntStream;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewDomainServiceImpl implements ReviewDomainService {

    private final ReviewRepository reviewRepository;

    @Override
    public void createOnboarding(
        List<Long> reviewIds,
        List<Long> perfumeIds,
        Long userId
    ) {
        List<Review> reviews = IntStream.range(0, reviewIds.size())
            .mapToObj(i -> Review.builder()
                .reviewId(reviewIds.get(i))
                .perfumeId(perfumeIds.get(i))
                .userId(userId)
                .content("")
                .reviewType(ReviewType.ONBOARDING)
                .build())
            .toList();

        reviewRepository.createOnboarding(reviews);
    }

    @Override
    public Long create(
        Long reviewId,
        Long perfumeId,
        Long userId,
        String content
    ) {
        Review review = Review.builder()
            .reviewId(reviewId)
            .perfumeId(perfumeId)
            .userId(userId)
            .content(content)
            .reviewType(ReviewType.REVIEW)
            .build();

        return reviewRepository.create(review);
    }

    @Override
    public List<Review> findAllByUserId(Long userId) {
        return reviewRepository.findAllByUserId(userId);
    }

    @Override
    public void updateContent(
        Long reviewId,
        Long userId,
        String content
    ) {
        Review review = reviewRepository.getByIdAndUserId(
            reviewId,
            userId
        );

        review.updateContent(content);

        reviewRepository.save(review);
    }

    @Override
    public Review getByIdAndUserId(
        Long reviewId,
        Long userId
    ) {
        return reviewRepository.getByIdAndUserId(
            reviewId,
            userId
        );
    }

    @Override
    public void delete(Review review) {
        reviewRepository.delete(review);
    }

}
