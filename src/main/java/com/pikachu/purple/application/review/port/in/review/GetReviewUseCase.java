package com.pikachu.purple.application.review.port.in.review;

import com.pikachu.purple.application.review.common.dto.ReviewByUserDTO;

public interface GetReviewUseCase {

    Result invoke(Long userId, Long perfumeId);

    record Result(ReviewByUserDTO reviewByUserDTO) {}

}
