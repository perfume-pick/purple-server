package com.pikachu.purple.application.rating.service.application;

import static com.pikachu.purple.support.security.SecurityProvider.getCurrentUserAuthentication;

import com.pikachu.purple.application.rating.port.in.RatingUpdateUseCase;
import com.pikachu.purple.application.rating.service.domain.RatingDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RatingUpdateApplicationService implements RatingUpdateUseCase {

    private final RatingDomainService ratingDomainService;

    @Override
    public void invoke(Command command) {
        Long userId = getCurrentUserAuthentication().userId();

        ratingDomainService.updateScore(
            userId,
            command.reviewId(),
            command.score()
        );
    }

}
