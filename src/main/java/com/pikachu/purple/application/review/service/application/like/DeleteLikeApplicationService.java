package com.pikachu.purple.application.review.service.application.like;

import static com.pikachu.purple.support.security.SecurityProvider.getCurrentUserAuthentication;

import com.pikachu.purple.application.review.port.in.like.DeleteLikeUseCase;
import com.pikachu.purple.application.review.port.in.review.DecreaseLikeCountUseCase;
import com.pikachu.purple.application.review.service.domain.LikeDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeleteLikeApplicationService implements DeleteLikeUseCase {

    private final LikeDomainService likeDomainService;
    private final DecreaseLikeCountUseCase decreaseLikeCountUseCase;

    @Transactional
    @Override
    public void invoke(Command command) {
        Long userId = getCurrentUserAuthentication().userId();

        likeDomainService.delete(
            userId,
            command.reviewId()
        );

        decreaseLikeCountUseCase.invoke(new DecreaseLikeCountUseCase.Command(command.reviewId()));
    }

}
