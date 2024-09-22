package com.pikachu.purple.bootstrap.onboarding.api;

import com.pikachu.purple.bootstrap.common.security.Secured;
import com.pikachu.purple.bootstrap.onboarding.dto.request.CreateStarRatingOnboardingRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Tag(name = "Onboarding", description = "Onboarding API")
@RequestMapping(value = "/perpicks/onboarding", produces = "application/json")
public interface OnboardingApi {

    @Secured
    @Operation(summary = "온보딩에서 향수 평가 저장")
    @PostMapping("/star-ratings")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void createOnboarding(@RequestBody @Valid CreateStarRatingOnboardingRequest request);

}
