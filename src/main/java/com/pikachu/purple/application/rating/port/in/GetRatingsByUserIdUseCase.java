package com.pikachu.purple.application.rating.port.in;

import com.pikachu.purple.domain.review.StarRating;
import java.util.List;

public interface GetRatingsByUserIdUseCase {

    Result invoke(Command command);

    record Command(Long userId) {}

    record Result(List<StarRating> starRatings) {}

}
