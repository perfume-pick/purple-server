package com.pikachu.purple.bootstrap.rating.controller;

import com.pikachu.purple.application.rating.port.in.RatingCreateUseCase;

import com.pikachu.purple.application.rating.port.in.RatingCreateUseCase.OnboardingCommand;
import com.pikachu.purple.bootstrap.rating.api.RatingApi;
import com.pikachu.purple.bootstrap.rating.dto.request.RatingCreateOnboardingRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RatingController implements RatingApi {

    private final RatingCreateUseCase ratingCreateUseCase;

    @Override
    public void createOnboarding(RatingCreateOnboardingRequest request) {
        ratingCreateUseCase.createOnboarding(
            new OnboardingCommand(request.ratingValues())
        );
    }

}
