package com.pikachu.purple.application.review.port.in.review;

import java.util.List;

public interface GetTopThreeReviewedBrandsUseCase {

    Result invoke(Long userId);

    record Result(List<ReviewedBrandDTO> reviewedBrandDTOs) {}

    record ReviewedBrandDTO(
        int order,
        String brandName,
        int reviewCounts
    ) {}

}
