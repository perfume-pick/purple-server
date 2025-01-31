package com.pikachu.purple.application.review.service.application.like;

import static com.pikachu.purple.support.security.SecurityProvider.getCurrentUserAuthentication;

import com.pikachu.purple.application.review.port.in.like.DeleteLikeUseCase;
import com.pikachu.purple.application.review.port.in.review.DecreaseReviewLikeCountUseCase;
import com.pikachu.purple.application.review.service.domain.LikeDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
class DeleteLikeService implements DeleteLikeUseCase {

    private final LikeDomainService likeDomainService;
    private final DecreaseReviewLikeCountUseCase decreaseReviewLikeCountUseCase;

    @Transactional
    @Override
    public void invoke(Long reviewId) {
        Long userId = getCurrentUserAuthentication().userId();

        likeDomainService.delete(
            userId,
            reviewId
        );

        decreaseReviewLikeCountUseCase.invoke(reviewId);
    }

}
