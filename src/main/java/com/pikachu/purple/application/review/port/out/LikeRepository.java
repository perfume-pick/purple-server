package com.pikachu.purple.application.review.port.out;

import com.pikachu.purple.domain.review.Like;
import java.util.List;

public interface LikeRepository {

    void create(Long userId, Long reviewId);

    List<Like> findAllByUserId(Long userId);

    List<Like> findAllByUserIdAndPerfumeId(Long userId, Long perfumeId);

    void validateNotExist(Long userId, Long reviewId);

    void delete(Long userId, Long reviewId);

    void deleteAll(Long reviewId);

    boolean isExist(Long userId, Long reviewId);

}
