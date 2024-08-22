package com.pikachu.purple.application.rating.service.domain;

import com.pikachu.purple.bootstrap.review.vo.RatingValue;
import com.pikachu.purple.domain.rating.Rating;
import java.util.List;

public interface RatingDomainService {

    void createOnboarding(
        List<Long> ratingIds,
        Long userId,
        List<Long> reviewIds,
        List<RatingValue> ratingValues
    );

    void create(
        Long ratingId,
        Long userId,
        Long reviewId,
        Double score
    );

    List<Rating> getAllByUserId(Long userId);

    void updateScore(
        Long userId,
        Long reviewId,
        Double score
    );

}
