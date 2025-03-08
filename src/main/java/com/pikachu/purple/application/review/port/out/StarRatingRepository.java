package com.pikachu.purple.application.review.port.out;

import com.pikachu.purple.domain.review.StarRating;
import java.util.List;

public interface StarRatingRepository {

    StarRating create(Long starRatingId, Long userId, Long perfumeId, int score);

    List<StarRating> createAll(Long userId, List<StarRating> starRatings);

    StarRating findByStarRatingId(Long starRatingId);

    StarRating findByUserIdAndPerfumeId(Long userId, Long perfumeId);

    List<StarRating> findAll();

    List<StarRating> findAllByPerfumeId(Long perfumeId);

    List<StarRating> findAllByUserId(Long userId);

    StarRating updateScore(
        Long userId,
        Long perfumeId,
        int score
    );

    StarRating deleteById(Long starRatingId);

}
