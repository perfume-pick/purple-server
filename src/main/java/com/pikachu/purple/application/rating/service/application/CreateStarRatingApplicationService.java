package com.pikachu.purple.application.rating.service.application;

import static com.pikachu.purple.support.security.SecurityProvider.getCurrentUserAuthentication;

import com.pikachu.purple.application.rating.port.in.CreateStarRatingUseCase;
import com.pikachu.purple.application.rating.service.domain.StarRatingDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreateStarRatingApplicationService implements CreateStarRatingUseCase {

    private final StarRatingDomainService starRatingDomainService;

    @Override
    @Transactional
    public void invoke(Command command) {
        Long userId = getCurrentUserAuthentication().userId();

        starRatingDomainService.create(
            userId,
            command.perfumeId(),
            command.score()
        );
    }

}
