package com.pikachu.purple.application.review.service.domain;

import com.pikachu.purple.domain.review.Review;
import com.pikachu.purple.domain.review.enums.ReviewType;
import java.util.List;

public interface ReviewDomainService {

    Review create(
        Long userId,
        Long perfumeId,
        String content,
        ReviewType reviewType
    );

    Review updateContent(
        Long reviewId,
        String content
    );

    void deleteById(Long Id);

    void createReviewMoods(
        Long reviewId,
        List<String> moodNames
    );
}
