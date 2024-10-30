package com.pikachu.purple.application.review.port.in.review;

import com.pikachu.purple.application.review.common.dto.ReviewedBrandDTO;
import java.util.List;

public interface GetTopThreeReviewedBrandsUseCase {

    Result invoke();

    record Result(List<ReviewedBrandDTO> reviewedBrandDTOs) {}

}
