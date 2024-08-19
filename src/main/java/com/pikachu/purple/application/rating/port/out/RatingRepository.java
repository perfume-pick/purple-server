package com.pikachu.purple.application.rating.port.out;

import com.pikachu.purple.domain.rating.Rating;
import java.util.List;

public interface RatingRepository {

    void createOnboarding(List<Rating> ratings);

    void create(Rating rating);

    List<Rating> getAllByUserId(Long userId);

    Rating getByUserIdAndReviewId(
        Long userId,
        Long reviewId
    );

    void save(Rating rating);

}
