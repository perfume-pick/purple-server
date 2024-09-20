package com.pikachu.purple.application.rating.service.application;

import static com.pikachu.purple.support.security.SecurityProvider.getCurrentUserAuthentication;

import com.pikachu.purple.application.rating.port.in.CreateStarRatingOnboardingUseCase;
import com.pikachu.purple.application.rating.service.domain.StarRatingDomainService;
import com.pikachu.purple.application.statistic.port.in.UpdateStarRatingStatisticsUseCase;
import com.pikachu.purple.application.user.port.in.CreateUserAccordUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreateStarRatingOnboardingApplicationService implements
    CreateStarRatingOnboardingUseCase {

    private final CreateUserAccordUseCase createUserAccordUseCase;
    private final StarRatingDomainService starRatingDomainService;
    private final UpdateStarRatingStatisticsUseCase updateStarRatingStatisticsUseCase;

    @Override
    @Transactional
    public void invoke(Command command) {
        Long userId = getCurrentUserAuthentication().userId();

        starRatingDomainService.createOnboarding(
            userId,
            command.starRatingInfos()
        );

        updateStarRatingStatisticsUseCase.invoke(
            new UpdateStarRatingStatisticsUseCase.Command(
                command.starRatingInfos()
            )
        );

        createUserAccordUseCase.invoke();
    }

}
