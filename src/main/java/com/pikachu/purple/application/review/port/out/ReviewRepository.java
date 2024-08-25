package com.pikachu.purple.application.review.port.out;

import com.pikachu.purple.domain.review.Review;
import java.util.List;

public interface ReviewRepository {

    void createOnboarding(List<Review> reviews);

    void create(Review review);

    List<Review> findAllByUserId(Long userId);

    Review getByIdAndUserId(
        Long reviewId,
        Long userId
    );

    void save(Review review);

    void delete(Review review);

}
