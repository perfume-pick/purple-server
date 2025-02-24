package com.pikachu.purple.bootstrap.onboarding.controller;

import static com.pikachu.purple.support.security.SecurityProvider.getCurrentUserAuthentication;

import com.pikachu.purple.application.review.port.in.starrating.CreateStarRatingsOnboardingUseCase;
import com.pikachu.purple.bootstrap.onboarding.api.OnboardingApi;
import com.pikachu.purple.bootstrap.onboarding.dto.request.CreateStarRatingOnboardingRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OnboardingController implements OnboardingApi {

    private final CreateStarRatingsOnboardingUseCase createStarRatingsOnboardingUseCase;

    @Override
    public void createOnboarding(CreateStarRatingOnboardingRequest request) {
        Long userId = getCurrentUserAuthentication().userId();

        createStarRatingsOnboardingUseCase.createAll(
            userId,
            request.starRatingVOs()
        );
    }

}
