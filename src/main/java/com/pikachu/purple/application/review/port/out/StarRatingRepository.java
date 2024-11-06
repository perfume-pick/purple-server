package com.pikachu.purple.application.review.port.out;

import com.pikachu.purple.domain.review.StarRating;
import java.util.List;

public interface StarRatingRepository {

    void createOnboarding(Long userId, List<StarRating> starRatings);

    StarRating create(Long starRatingId, Long userId, Long perfumeId, int score);

    List<StarRating> findAllWithPerfumeAndPerfumeAccordByUserId(Long userId);

    StarRating updateScore(
        Long userId,
        Long perfumeId,
        int score
    );

    StarRating deleteById(Long starRatingId);

    StarRating findByUserIdAndPerfumeId(
        Long userId,
        Long perfumeId
    );

    List<StarRating> findAllByUpdatedDate(String updatedDate);

    List<StarRating> findAll(Long perfumeId);

    List<StarRating> findAllOrderByLikeCountDesc(Long userId);

    List<StarRating> findAllByUserId(Long userId);

    List<StarRating> findAllOrderByScoreDesc(Long userId);

    List<StarRating> findALlOrderByScoreAsc(Long userId);

}
