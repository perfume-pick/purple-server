package com.pikachu.purple.application.review.port.in.review;

import com.pikachu.purple.domain.review.Review;
import java.util.List;

public interface GetDetailedReviewsUseCase {

    Result invoke();

    record Result(List<Review> reviews) {}

}
