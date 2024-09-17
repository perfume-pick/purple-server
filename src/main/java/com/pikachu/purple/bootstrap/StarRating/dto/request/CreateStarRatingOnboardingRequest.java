package com.pikachu.purple.bootstrap.StarRating.dto.request;

import com.pikachu.purple.bootstrap.StarRating.vo.StarRatingInfo;
import jakarta.validation.Valid;
import java.util.List;

public record CreateStarRatingOnboardingRequest(@Valid List<StarRatingInfo> starRatingInfos) {

}
