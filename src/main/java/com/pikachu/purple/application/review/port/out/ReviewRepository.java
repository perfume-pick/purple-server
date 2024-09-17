package com.pikachu.purple.application.review.port.out;

import com.pikachu.purple.domain.review.Review;
import java.util.List;

public interface ReviewRepository {

    Review create(
        Long userId,
        Long perfumeId,
        Review review
    );

    List<Review> findAllByUserId(Long userId);

    Review findWithPerfumeById(Long reviewId);

    Review updateContent(
        Long reviewId,
        String content
    );

    Review deleteById(Long id);

    void createReviewMoods(
        Long reviewId,
        List<String> moodNames
    );
}
