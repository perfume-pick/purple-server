package com.pikachu.purple.application.review.service.application.like;

import static com.pikachu.purple.support.security.SecurityProvider.getCurrentUserAuthentication;

import com.pikachu.purple.application.review.port.in.like.CreateLikeUseCase;
import com.pikachu.purple.application.review.port.in.review.IncreaseLikeCountUseCase;
import com.pikachu.purple.application.review.service.domain.LikeDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreateLikeApplicationService implements CreateLikeUseCase {

    private final LikeDomainService likeDomainService;
    private final IncreaseLikeCountUseCase increaseLikeCountUseCase;

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

        increaseLikeCountUseCase.invoke(new IncreaseLikeCountUseCase.Command(reviewId));
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
