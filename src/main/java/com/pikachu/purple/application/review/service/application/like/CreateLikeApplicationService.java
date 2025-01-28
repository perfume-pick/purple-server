package com.pikachu.purple.application.review.service.application.like;

import static com.pikachu.purple.support.security.SecurityProvider.getCurrentUserAuthentication;

import com.pikachu.purple.application.review.port.in.like.CreateLikeUseCase;
import com.pikachu.purple.application.review.port.in.review.IncreaseReviewLikeCountUseCase;
import com.pikachu.purple.application.review.service.domain.LikeDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
class CreateLikeApplicationService implements CreateLikeUseCase {

    private final LikeDomainService likeDomainService;
    private final IncreaseReviewLikeCountUseCase increaseReviewLikeCountUseCase;

    @Transactional
    @Override
    public void invoke(Long reviewId) {
        Long userId = getCurrentUserAuthentication().userId();

        validateNotExist(
            userId,
            reviewId
        );


        likeDomainService.create(
            userId,
            reviewId
        );

        increaseReviewLikeCountUseCase.invoke(reviewId);
    }

    private void validateNotExist(
        Long userId,
        Long reviewId
    ) {
        likeDomainService.validateNotExist(
            userId,
            reviewId
        );
    }

}
