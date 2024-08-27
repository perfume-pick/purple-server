package com.pikachu.purple.bootstrap.rating.dto.request;

import com.pikachu.purple.bootstrap.rating.vo.RatingInfo;
import jakarta.validation.Valid;
import java.util.List;

public record RatingCreateOnboardingRequest(@Valid List<RatingInfo> ratingInfos) {

}
