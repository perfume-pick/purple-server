package com.pikachu.purple.application.rating.service.domain;

import com.pikachu.purple.bootstrap.rating.vo.RatingInfo;
import com.pikachu.purple.domain.rating.Rating;
import java.util.List;

public interface RatingDomainService {

    void createOnboarding(
        List<Long> ratingIds,
        Long userId,
        List<RatingInfo> ratingInfos
    );

    Rating create(
        Long ratingId,
        Long userId,
        Long perfumeId,
        int score
    );

    List<Rating> getAllByUserId(Long userId);

    void updateScore(
        Long userId,
        Long ratingId,
        int score
    );

    Rating getByIdAndUserId(
        Long ratingId,
        Long userId
    );

    void delete(Rating rating);

}
