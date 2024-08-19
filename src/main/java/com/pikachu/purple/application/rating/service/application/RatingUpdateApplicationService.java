package com.pikachu.purple.application.rating.service.application;

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
        ratingDomainService.updateScore(
            command.userId(),
            command.reviewId(),
            command.score()
        );
    }

}
