package com.pikachu.purple.application.review.service.application.starrating;

import static com.pikachu.purple.support.security.SecurityProvider.getCurrentUserAuthentication;

import com.pikachu.purple.application.perfume.port.in.perfume.RecalculatePerfumeAverageScoresUseCase;
import com.pikachu.purple.application.review.port.in.starrating.CreateStarRatingOnboardingUseCase;
import com.pikachu.purple.application.review.service.domain.StarRatingDomainService;
import com.pikachu.purple.application.statistic.port.in.starratingstatistic.IncreaseStarRatingStatisticsUseCase;
import com.pikachu.purple.application.user.port.in.useraccord.CreateUserAccordOnboardingUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreateStarRatingOnboardingApplicationService implements
    CreateStarRatingOnboardingUseCase {

    private final CreateUserAccordOnboardingUseCase createUserAccordOnboardingUseCase;
    private final StarRatingDomainService starRatingDomainService;
    private final IncreaseStarRatingStatisticsUseCase increaseStarRatingStatisticsUseCase;
    private final RecalculatePerfumeAverageScoresUseCase recalculatePerfumeAverageScoresUseCase;

    @Override
    @Transactional
    public void invoke(Command command) {
        Long userId = getCurrentUserAuthentication().userId();

        starRatingDomainService.createOnboarding(
            userId,
            command.starRatingVOs()
        );

        increaseStarRatingStatisticsUseCase.invoke(
            new IncreaseStarRatingStatisticsUseCase.Command(
                command.starRatingVOs()
            )
        );

        // TODO: 별점 갱신 후 콜백으로 처리
//        List<Long> perfumeIds = command.starRatingVOs().stream()
//            .map(StarRatingVO::perfumeId).toList();
//        recalculatePerfumeAverageScoresUseCase.invoke(
//            new RecalculatePerfumeAverageScoresUseCase.Command(perfumeIds));

        createUserAccordOnboardingUseCase.invoke();
    }

}
