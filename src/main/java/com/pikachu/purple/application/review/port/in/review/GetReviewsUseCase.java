package com.pikachu.purple.application.review.port.in.review;

import com.pikachu.purple.application.review.common.dto.ReviewDTO;
import java.util.List;

public interface GetReviewsUseCase {

    Result findAll(
        Long perfumeId,
        String sortType
    );

    record Result(List<ReviewDTO> reviewDTOs) {}

}
