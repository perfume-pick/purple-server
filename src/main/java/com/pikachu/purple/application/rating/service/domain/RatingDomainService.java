package com.pikachu.purple.application.rating.service.domain;

import com.pikachu.purple.bootstrap.user.vo.RatingValue;
import java.util.List;

public interface RatingDomainService {

    void create(
        Long ratingId,
        Long userId,
        List<RatingValue> ratingValueList
    );

}
