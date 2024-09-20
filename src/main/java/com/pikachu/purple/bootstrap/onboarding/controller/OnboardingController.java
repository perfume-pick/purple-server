package com.pikachu.purple.bootstrap.onboarding.controller;

import com.pikachu.purple.application.rating.port.in.CreateStarRatingOnboardingUseCase;
import com.pikachu.purple.application.rating.port.in.CreateStarRatingOnboardingUseCase.Command;
import com.pikachu.purple.bootstrap.onboarding.api.OnboardingApi;
import com.pikachu.purple.bootstrap.onboarding.dto.request.CreateStarRatingOnboardingRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OnboardingController implements OnboardingApi {

    private final CreateStarRatingOnboardingUseCase createStarRatingOnboardingUseCase;

    @Override
    public void createOnboarding(CreateStarRatingOnboardingRequest request) {
        createStarRatingOnboardingUseCase.invoke(
            new Command(
                request.starRatingInfos()
            )
        );
    }

}