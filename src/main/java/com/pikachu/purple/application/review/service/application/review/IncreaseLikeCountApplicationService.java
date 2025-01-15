package com.pikachu.purple.application.review.service.application.review;

import com.pikachu.purple.application.review.port.in.review.IncreaseLikeCountUseCase;
import com.pikachu.purple.application.review.service.domain.ReviewDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IncreaseLikeCountApplicationService implements IncreaseLikeCountUseCase {

    private final ReviewDomainService reviewDomainService;

    @Override
    public void invoke(Long reviewId) {
        reviewDomainService.increaseLikeCount(reviewId);
    }

}
