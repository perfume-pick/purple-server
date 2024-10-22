package com.pikachu.purple.application.review.port.out;

import com.pikachu.purple.domain.review.Like;

public interface LikeRepository {

    Like find(Long userId, Long reviewId);

    void create(Long userId, Long reviewId);

    void validateNotExist(Long userId, Long reviewId);

    void delete(Long userId, Long reviewId);

}
