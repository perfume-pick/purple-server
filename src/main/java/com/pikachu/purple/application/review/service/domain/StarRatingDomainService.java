package com.pikachu.purple.application.review.service.domain;

import com.pikachu.purple.bootstrap.onboarding.vo.StarRatingVO;
import com.pikachu.purple.domain.review.StarRating;
import java.util.List;

public interface StarRatingDomainService {

    void createOnboarding(
        Long userId,
        List<StarRatingVO> starRatingVOs
    );

    StarRating create(
        Long userId,
        Long perfumeId,
        int score
    );

    List<StarRating> findAllWithPerfumeAndPerfumeAccordByUserId(Long userId);

    StarRating updateScore(
        Long userId,
        Long ratingId,
        int score
    );

    void deleteById(Long starRatingId);

    StarRating findByUserIdAndPerfumeId(
        Long userId,
        Long perfumeId
    );
}
