package com.pikachu.purple.application.rating.port.out;

import com.pikachu.purple.domain.rating.Rating;
import java.util.List;

public interface RatingRepository {

    void create(List<Rating> ratings);

    List<Rating> getAllByUserId(Long userId);

}
