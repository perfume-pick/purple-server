package com.pikachu.purple.application.review.port.in.starrating;

import com.pikachu.purple.domain.review.StarRating;
import java.util.List;

public interface GetStarRatingsUseCase {

    Result invoke();

    record Result(List<StarRating> starRatings) {}

}
