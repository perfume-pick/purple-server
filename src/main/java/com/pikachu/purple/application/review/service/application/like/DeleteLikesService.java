package com.pikachu.purple.application.review.service.application.like;

import com.pikachu.purple.application.review.port.in.like.DeleteLikesUseCase;
import com.pikachu.purple.application.review.service.domain.LikeDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
class DeleteLikesService implements DeleteLikesUseCase {

    private final LikeDomainService likeDomainService;

    @Transactional
    @Override
    public void invoke(Long reviewId) {
        likeDomainService.deleteAll(reviewId);
    }

}
