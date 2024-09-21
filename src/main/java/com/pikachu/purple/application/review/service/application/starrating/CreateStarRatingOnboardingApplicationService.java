package com.pikachu.purple.application.review.service.application.starrating;

import static com.pikachu.purple.support.security.SecurityProvider.getCurrentUserAuthentication;

import com.pikachu.purple.application.review.port.in.starrating.CreateStarRatingOnboardingUseCase;
import com.pikachu.purple.application.review.service.domain.StarRatingDomainService;
import com.pikachu.purple.application.statistic.port.in.starratingstatistic.UpdateStarRatingStatisticsUseCase;
import com.pikachu.purple.application.user.port.in.useraccord.CreateUserAccordUseCase;
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
            command.starRatingVOs()
        );

        updateStarRatingStatisticsUseCase.invoke(
            new UpdateStarRatingStatisticsUseCase.Command(
                command.starRatingVOs()
            )
        );

        createUserAccordUseCase.invoke();
    }

}