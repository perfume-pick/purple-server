package com.pikachu.purple.application.rating.port.in;

import com.pikachu.purple.domain.rating.Rating;
import java.util.List;

public interface RatingGetByUserIdUseCase {

    Result invoke(Long userId);

    record Result(List<Rating> ratingList) {

    }

}
