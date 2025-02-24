package com.pikachu.purple.application.review.service.application.like;

import static com.pikachu.purple.support.security.SecurityProvider.getCurrentUserAuthentication;

import com.pikachu.purple.application.review.port.in.like.CreateLikeUseCase;
import com.pikachu.purple.application.review.port.in.review.IncreaseReviewLikeCountUseCase;
import com.pikachu.purple.application.review.port.out.LikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
class CreateLikeService implements CreateLikeUseCase {

    private final LikeRepository likeRepository;
    private final IncreaseReviewLikeCountUseCase increaseReviewLikeCountUseCase;

    @Transactional
    @Override
    public void invoke(Long reviewId) {
        Long userId = getCurrentUserAuthentication().userId();

        likeRepository.validateNotExist(
            userId,
            reviewId
        );


        likeRepository.create(
            userId,
            reviewId
        );

        increaseReviewLikeCountUseCase.invoke(reviewId);
    }

}
