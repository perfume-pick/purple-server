package com.pikachu.purple.bootstrap.review.dto.request;

import com.pikachu.purple.bootstrap.review.vo.RatingValue;
import java.util.List;

public record ReviewCreateOnboardingRequest(List<RatingValue> ratingValues) {

}
