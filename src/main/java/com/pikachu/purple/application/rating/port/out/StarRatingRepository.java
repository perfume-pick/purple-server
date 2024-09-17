package com.pikachu.purple.application.rating.port.out;

import com.pikachu.purple.bootstrap.StarRating.vo.StarRatingInfo;
import com.pikachu.purple.domain.review.StarRating;
import java.util.List;

public interface StarRatingRepository {

    void createOnboarding(Long userId, List<StarRatingInfo> starRatings);

    /*
    StarRating 반환
     */
    StarRating create(Long userId, Long perfumeId, int score);

    List<StarRating> findAllWithPerfumeAndPerfumeAccordByUserId(Long userId);

    void updateScore(
        Long userId,
        Long perfumeId,
        int score
    );

    void deleteByUserIdAndPerfumeId(
        Long userId,
        Long perfumeId
    );

}
