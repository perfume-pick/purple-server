package com.pikachu.purple.application.rating.service.application;

import static com.pikachu.purple.support.security.SecurityProvider.getCurrentUserAuthentication;

import com.pikachu.purple.application.rating.port.in.UpdateRatingUseCase;
import com.pikachu.purple.application.rating.service.domain.StarRatingDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateRatingApplicationService implements UpdateRatingUseCase {

    private final StarRatingDomainService starRatingDomainService;

    @Override
    public void invoke(Command command) {
        Long userId = getCurrentUserAuthentication().userId();

        starRatingDomainService.updateScore(
            userId,
            command.perfumeId(),
            command.score()
        );
    }

}
