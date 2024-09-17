package com.pikachu.purple.bootstrap.StarRating.controller;

import com.pikachu.purple.application.rating.port.in.CreateStarRatingOnboardingUseCase;
import com.pikachu.purple.application.rating.port.in.CreateStarRatingOnboardingUseCase.Command;
import com.pikachu.purple.bootstrap.StarRating.api.StarRatingApi;
import com.pikachu.purple.bootstrap.StarRating.dto.request.CreateStarRatingOnboardingRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class StarRatingController implements StarRatingApi {

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
