package com.pikachu.purple.application.rating.service.application;

import static com.pikachu.purple.support.security.SecurityProvider.getCurrentUserAuthentication;

import com.pikachu.purple.application.perfume.port.in.GetPerfumesByIdsUseCase;
import com.pikachu.purple.application.rating.port.in.CreateStarRatingOnboardingUseCase;
import com.pikachu.purple.application.rating.service.domain.StarRatingDomainService;
import com.pikachu.purple.application.user.port.in.CreateUserAccordUseCase;
import com.pikachu.purple.application.user.port.in.GetUserByIdUseCase;
import com.pikachu.purple.bootstrap.StarRating.vo.StarRatingInfo;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreateStarRatingOnboardingApplicationService implements
    CreateStarRatingOnboardingUseCase {

    private final GetUserByIdUseCase getUserByIdUseCase;
    private final GetPerfumesByIdsUseCase getPerfumesByIdsUseCase;
    private final CreateUserAccordUseCase createUserAccordUseCase;
    private final StarRatingDomainService starRatingDomainService;

    @Override
    @Transactional
    public void invoke(Command command) {
        Long userId = getCurrentUserAuthentication().userId();

        GetUserByIdUseCase.Result user = getUserByIdUseCase.invoke(new GetUserByIdUseCase.Command(userId));

        List<Long> perfumeIds = command.starRatingInfos().stream()
            .map(StarRatingInfo::perfumeId)
            .toList();

        GetPerfumesByIdsUseCase.Result perfumes = getPerfumesByIdsUseCase.invoke(new GetPerfumesByIdsUseCase.Command(perfumeIds));

        starRatingDomainService.createOnboarding(
            user.user(),
            perfumes.perfumes(),
            command.starRatingInfos()
        );

        createUserAccordUseCase.invoke();
    }

}
