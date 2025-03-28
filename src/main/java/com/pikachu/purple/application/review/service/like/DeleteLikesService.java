package com.pikachu.purple.application.review.service.like;

import com.pikachu.purple.application.review.port.in.like.DeleteLikesUseCase;
import com.pikachu.purple.application.review.port.out.LikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
class DeleteLikesService implements DeleteLikesUseCase {

    private final LikeRepository likeRepository;

    @Transactional
    @Override
    public void deleteAll(Long reviewId) {
        likeRepository.deleteAll(reviewId);
    }

}
