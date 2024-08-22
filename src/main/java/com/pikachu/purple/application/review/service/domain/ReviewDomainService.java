package com.pikachu.purple.application.review.service.domain;

import com.pikachu.purple.domain.review.Review;
import java.util.List;

public interface ReviewDomainService {

    void createOnboarding(
        List<Long> reviewIds,
        List<Long> longs,
        Long userId
    );

    Long create(
        Long reviewId,
        Long perfumeId,
        Long userId,
        String comment
    );

    List<Review> findAllByUserId(Long userId);

    void updateContent(
        Long reviewId,
        Long userId,
        String content
    );

    Review getByIdAndUserId(
        Long reviewId,
        Long userId
    );

    void delete(Review review);

}