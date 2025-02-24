package com.pikachu.purple.application.review.service.application.review;

import com.pikachu.purple.application.review.port.in.review.IncreaseReviewLikeCountUseCase;
import com.pikachu.purple.application.review.port.out.ReviewRepository;
import com.pikachu.purple.infrastructure.redis.annotation.DistributedLock;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class IncreaseReviewLikeCountService implements IncreaseReviewLikeCountUseCase {

    private final ReviewRepository reviewRepository;

    @Override
    @DistributedLock(
        name = "Review",
        key = "T(String).valueOf(#reviewId).concat('-likeCount')"
    )
    public void invoke(Long reviewId) {
        reviewRepository.increaseLikeCount(reviewId);
    }

}
