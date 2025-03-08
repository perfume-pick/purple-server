package com.pikachu.purple.application.review.service.like;

import com.pikachu.purple.application.review.port.in.like.CheckLikeUseCase;
import com.pikachu.purple.application.review.port.out.LikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
class CheckLikeService implements CheckLikeUseCase {

    private final LikeRepository likeRepository;

    @Transactional
    @Override
    public Result check(
        Long userId,
        Long reviewId
    ) {
        boolean isLiked = likeRepository.isExist(
            userId,
            reviewId
        );

        return new Result(isLiked);
    }

}
