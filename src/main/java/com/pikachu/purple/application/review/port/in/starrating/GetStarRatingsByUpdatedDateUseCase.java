package com.pikachu.purple.application.review.port.in.starrating;

import com.pikachu.purple.domain.review.StarRating;
import java.util.List;

public interface GetStarRatingsByUpdatedDateUseCase {

    Result invoke(Command command);

    record Command(String updatedDate) {}

    record Result(List<StarRating> starRatings) {}

}
