package com.pikachu.purple.bootstrap.onboarding.dto.request;

import com.pikachu.purple.bootstrap.onboarding.vo.StarRatingInfo;
import jakarta.validation.Valid;
import java.util.List;

public record CreateStarRatingOnboardingRequest(@Valid List<StarRatingInfo> starRatingInfos) {

}
