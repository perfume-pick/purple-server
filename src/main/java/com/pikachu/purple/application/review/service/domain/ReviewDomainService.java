package com.pikachu.purple.application.review.service.domain;

import com.pikachu.purple.domain.review.Review;
import com.pikachu.purple.domain.review.enums.ReviewType;
import java.util.List;

public interface ReviewDomainService {

    void create(
        Long perfumeId,
        Long userId,
        String content,
        ReviewType reviewType
    );

    List<Review> findAllByUserId(Long userId);

    void updateContent(
        Long reviewId,
        Long userId,
        String content
    );

    Review findById(Long reviewId);

    void delete(Review review);

}
