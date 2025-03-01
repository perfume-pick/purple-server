package com.pikachu.purple.application.review.port.out;

import com.pikachu.purple.domain.review.StarRating;
import java.util.List;

public interface StarRatingRepository {

    void createAll(Long userId, List<StarRating> starRatings);

    StarRating create(Long starRatingId, Long userId, Long perfumeId, int score);

    List<StarRating> findAllWithPerfume(Long userId);

    StarRating updateScore(
        Long userId,
        Long perfumeId,
        int score
    );

    StarRating deleteById(Long starRatingId);

    StarRating find(
        Long userId,
        Long perfumeId
    );

    StarRating findWithPerfume(
        Long userId,
        Long perfumeId
    );

    List<StarRating> findAll();

    List<StarRating> findAll(Long perfumeId);

    List<StarRating> findAllOrderByLikeCountDesc(Long userId);

    List<StarRating> findAllByUserId(Long userId);

    List<StarRating> findAllOrderByScoreDesc(Long userId);

    List<StarRating> findAllOrderByScoreAsc(Long userId);

}
