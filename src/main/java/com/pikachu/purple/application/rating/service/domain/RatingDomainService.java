package com.pikachu.purple.application.rating.service.domain;

import com.pikachu.purple.bootstrap.rating.vo.RatingInfo;
import com.pikachu.purple.domain.review.StarRating;
import java.util.List;

public interface RatingDomainService {

    void createOnboarding(
        Long userId,
        List<RatingInfo> ratingInfos
    );

    void create(
        Long userId,
        Long perfumeId,
        int score
    );

    List<StarRating> getAllByUserId(Long userId);

    void updateScore(
        Long userId,
        Long ratingId,
        int score
    );

    StarRating findByPerfumeIdAndUserId(
        Long perfumeId,
        Long userId
    );

    void delete(StarRating starRating);

}
