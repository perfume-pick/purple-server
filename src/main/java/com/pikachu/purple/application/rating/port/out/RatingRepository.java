package com.pikachu.purple.application.rating.port.out;

import com.pikachu.purple.domain.rating.Rating;
import java.util.List;

public interface RatingRepository {

    void createOnboarding(List<Rating> ratings);

    Long create(Rating rating);

    List<Rating> getAllByUserId(Long userId);

    Rating getById(Long ratingId);

    void save(Rating rating);

    Rating getByIdAndUserId(
        Long ratingId,
        Long userId
    );

    void delete(Rating rating);

}
