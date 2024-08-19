package com.pikachu.purple.application.review.service.application;

import static com.pikachu.purple.support.security.SecurityProvider.getCurrentUserAuthentication;

import com.pikachu.purple.application.rating.port.in.RatingUpdateUseCase;
import com.pikachu.purple.application.review.port.in.ReviewUpdateUseCase;
import com.pikachu.purple.application.review.service.domain.ReviewDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReviewUpdateApplicationService implements ReviewUpdateUseCase {

    private final RatingUpdateUseCase ratingUpdateUseCase;
    private final ReviewDomainService reviewDomainService;

    @Override
    @Transactional
    public void invoke(Command command) {
        Long userId = getCurrentUserAuthentication().userId();

        reviewDomainService.updateContent(
            command.reviewId(),
            userId,
            command.content()
        );

        ratingUpdateUseCase.invoke(
            new RatingUpdateUseCase.Command(
                userId, // FIXME userId의 호출은 어떻게 해야하는가?
                command.reviewId(),
                command.score()
            )
        );

    }

}
