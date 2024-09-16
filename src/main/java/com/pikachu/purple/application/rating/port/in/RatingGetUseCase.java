package com.pikachu.purple.application.rating.port.in;

import com.pikachu.purple.domain.review.StarRating;
import java.util.List;

public interface RatingGetUseCase {

    List<StarRating> getAllByUserId(Long userId);

}
