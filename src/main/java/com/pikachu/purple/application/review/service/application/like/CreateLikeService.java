package com.pikachu.purple.application.review.service.application.like;

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
    public void create(
        Long userId,
        Long reviewId
    ) {
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
