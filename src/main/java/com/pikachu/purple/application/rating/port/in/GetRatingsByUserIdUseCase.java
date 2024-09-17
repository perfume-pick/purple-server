package com.pikachu.purple.application.rating.port.in;

import com.pikachu.purple.domain.review.StarRating;
import java.util.List;

public interface GetRatingsByUserIdUseCase {

    List<StarRating> invoke(Long userId);

}
