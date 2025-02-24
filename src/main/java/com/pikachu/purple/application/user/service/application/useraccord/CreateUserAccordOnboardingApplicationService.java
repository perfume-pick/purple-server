package com.pikachu.purple.application.user.service.application.useraccord;

import static com.pikachu.purple.support.security.SecurityProvider.getCurrentUserAuthentication;

import com.pikachu.purple.application.perfume.util.UserAccordRecommender;
import com.pikachu.purple.application.review.port.in.starrating.GetStarRatingsUseCase;
import com.pikachu.purple.application.user.port.in.user.GetUserByIdUseCase;
import com.pikachu.purple.application.user.port.in.useraccord.CreateUserAccordOnboardingUseCase;
import com.pikachu.purple.application.user.service.domain.UserAccordDomainService;
import com.pikachu.purple.domain.user.UserAccord;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class CreateUserAccordOnboardingApplicationService implements
    CreateUserAccordOnboardingUseCase {

    private final GetUserByIdUseCase getUserByIdUseCase;
    private final GetStarRatingsUseCase getStarRatingsUseCase;
    private final UserAccordRecommender userAccordRecommender;
    private final UserAccordDomainService userAccordDomainService;

    @Override
    public void invoke() {
        Long userId = getCurrentUserAuthentication().userId();

        GetUserByIdUseCase.Result user = getUserByIdUseCase.invoke(userId);
        GetStarRatingsUseCase.Result starRatings = getStarRatingsUseCase.findAll(userId);

        List<UserAccord> userAccords = userAccordRecommender.recommend(
            user.user(),
            starRatings.starRatings()
        );

        userAccordDomainService.createAll(
            user.user().getId(),
            userAccords
        );
    }

}
