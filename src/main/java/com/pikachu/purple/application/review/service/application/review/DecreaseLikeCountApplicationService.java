package com.pikachu.purple.application.review.service.application.review;

import com.pikachu.purple.application.review.port.in.review.DecreaseLikeCountUseCase;
import com.pikachu.purple.application.review.service.domain.ReviewDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class DecreaseLikeCountApplicationService implements DecreaseLikeCountUseCase {

    private final ReviewDomainService reviewDomainService;

    @Override
    public void invoke(Long reviewId) {
        reviewDomainService.decreaseLikeCount(reviewId);
    }

}
