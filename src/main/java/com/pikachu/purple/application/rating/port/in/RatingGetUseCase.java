package com.pikachu.purple.application.rating.port.in;

import com.pikachu.purple.domain.rating.Rating;
import java.util.List;

public interface RatingGetUseCase {

    List<Rating> getAllByUserId(Long userId);

}
