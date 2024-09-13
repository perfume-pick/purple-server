package com.pikachu.purple.bootstrap.rating.controller;

import com.pikachu.purple.application.rating.port.in.RatingCreateOnboardingUseCase;
import com.pikachu.purple.application.rating.port.in.RatingCreateOnboardingUseCase.Command;
import com.pikachu.purple.bootstrap.rating.api.RatingApi;
import com.pikachu.purple.bootstrap.rating.dto.request.RatingCreateOnboardingRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RatingController implements RatingApi {

    private final RatingCreateOnboardingUseCase ratingCreateOnboardingUseCase;

    @Override
    public void createOnboarding(RatingCreateOnboardingRequest request) {
        ratingCreateOnboardingUseCase.invoke(
            new Command(request.ratingInfos())
        );
    }

}
