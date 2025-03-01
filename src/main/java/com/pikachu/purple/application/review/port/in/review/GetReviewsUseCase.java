package com.pikachu.purple.application.review.port.in.review;

import com.pikachu.purple.domain.review.Review;
import com.pikachu.purple.domain.review.enums.ReviewType;
import java.util.List;

public interface GetReviewsUseCase {

    Result findAll(ReviewType reviewType);

    Result findAllWithPerfumeAndReviewEvaluationAndMoodsAndIsComplainedAndIsLiked(
        Long currentUserId,
        Long perfumeId,
        String sortType
    );

    record Result(List<Review> reviews) {}

}
