package com.pikachu.purple.bootstrap.onboarding.dto.request;

import com.pikachu.purple.bootstrap.onboarding.vo.StarRatingVO;
import jakarta.validation.Valid;
import java.util.List;

public record CreateStarRatingOnboardingRequest(@Valid List<StarRatingVO> starRatingVOs) {

}
