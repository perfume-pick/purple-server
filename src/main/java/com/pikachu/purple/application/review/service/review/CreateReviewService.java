package com.pikachu.purple.application.review.service.review;

import com.pikachu.purple.application.review.port.in.review.CreateReviewUseCase;
import com.pikachu.purple.application.review.port.out.ReviewRepository;
import com.pikachu.purple.application.util.IdUtil;
import com.pikachu.purple.domain.review.Review;
import com.pikachu.purple.domain.review.enums.ReviewType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreateReviewService implements CreateReviewUseCase {

    private final ReviewRepository reviewRepository;

    @Transactional
    @Override
    public Result create(
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

        Review savedReview = reviewRepository.create(userId, perfumeId, review);
        return new Result(savedReview);
    }
}
