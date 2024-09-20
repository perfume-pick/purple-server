package com.pikachu.purple.application.rating.service.domain;

import com.pikachu.purple.bootstrap.onboarding.vo.StarRatingInfo;
import com.pikachu.purple.domain.review.StarRating;
import java.util.List;

public interface StarRatingDomainService {

    void createOnboarding(
        Long userId,
        List<StarRatingInfo> starRatingInfos
    );

    StarRating create(
        Long userId,
        Long perfumeId,
        int score
    );

    List<StarRating> findAllWithPerfumeAndPerfumeAccordByUserId(Long userId);

    void updateScore(
        Long userId,
        Long ratingId,
        int score
    );

    void deleteByUserIdAndPerfumeId(
        Long userId,
        Long perfumeId
    );

    StarRating findByUserIdAndPerfumeId(
        Long userId,
        Long perfumeId
    );
}
