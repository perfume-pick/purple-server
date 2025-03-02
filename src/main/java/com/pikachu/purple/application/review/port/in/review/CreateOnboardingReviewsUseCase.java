package com.pikachu.purple.application.review.port.in.review;

import com.pikachu.purple.domain.review.StarRating;
import java.util.List;

public interface CreateOnboardingReviewsUseCase {

    void createAll(List<StarRating> starRatings);

}
