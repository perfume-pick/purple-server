package com.pikachu.purple.application.rating.port.out;

import com.pikachu.purple.domain.review.StarRating;
import java.util.List;

public interface StarRatingRepository {

    void createOnboarding(List<StarRating> starRatings);

    /*
    StarRating 반환
     */
    StarRating create(StarRating starRating);

    List<StarRating> getAllByUserId(Long userId);

    StarRating getById(Long ratingId);

    void save(StarRating starRating);

    StarRating findByPerfumeIdAndUserId(
        Long perfumeId,
        Long userId
    );

    void delete(StarRating starRating);

}
