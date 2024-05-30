package com.pikachu.purple.application.rating.service.domain;

import com.pikachu.purple.bootstrap.user.vo.RatingValue;
import com.pikachu.purple.domain.rating.Rating;
import java.util.List;

public interface RatingDomainService {

    void create(
        List<Long> ratingIdList,
        Long userId,
        List<RatingValue> ratingValueList
    );

    List<Rating> getByUserId(Long userId);

}
