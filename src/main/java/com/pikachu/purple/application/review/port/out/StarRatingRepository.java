package com.pikachu.purple.application.review.port.out;

import com.pikachu.purple.domain.review.StarRating;
import com.pikachu.purple.domain.user.User;
import java.util.List;

public interface StarRatingRepository {

    StarRating create(Long starRatingId, Long userId, Long perfumeId, int score);

    void createAll(Long userId, List<StarRating> starRatings);

    StarRating find(Long starRatingId);

    StarRating find(Long userId, Long perfumeId);

    List<StarRating> findAll();

    List<StarRating> findAll(Long perfumeId);

    List<StarRating> findAllOrderByLikeCountDesc(Long userId);

    List<StarRating> findAllByUserId(Long userId);

    List<StarRating> findAllOrderByScoreDesc(Long userId);

    List<StarRating> findAllOrderByScoreAsc(Long userId);

    StarRating updateScore(
        Long userId,
        Long perfumeId,
        int score
    );

    StarRating deleteById(Long starRatingId);

}
