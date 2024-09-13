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
    public void create(
        Long perfumeId,
        Long userId,
        String content,
        ReviewType reviewType
    ) {
        Long reviewId = IdGenerator.generate();

        Review review = Review.builder()
            .reviewId(reviewId)
            .perfumeId(perfumeId)
            .userId(userId)
            .content(content)
            .reviewType(reviewType)
            .build();

        reviewRepository.create(review);
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
    public Review findById(Long reviewId) {
        return reviewRepository.findById(reviewId);
    }

    @Override
    public void delete(Review review) {
        reviewRepository.delete(review);
    }

}
