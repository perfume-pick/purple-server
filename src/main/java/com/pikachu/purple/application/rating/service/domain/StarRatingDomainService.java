package com.pikachu.purple.application.rating.service.domain;

import com.pikachu.purple.bootstrap.StarRating.vo.StarRatingInfo;
import com.pikachu.purple.domain.perfume.Perfume;
import com.pikachu.purple.domain.review.StarRating;
import com.pikachu.purple.domain.user.User;
import java.util.List;

public interface StarRatingDomainService {

    void createOnboarding(
        User user,
        List<Perfume> perfumes,
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

}
