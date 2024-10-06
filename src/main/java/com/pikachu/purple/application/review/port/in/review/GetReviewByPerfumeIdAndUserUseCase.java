package com.pikachu.purple.application.review.port.in.review;

import com.pikachu.purple.application.review.common.dto.ReviewByUserDTO;

public interface GetReviewByPerfumeIdAndUserUseCase {

    Result invoke(Command command);

    record Command(Long perfumeId) {}

    record Result(ReviewByUserDTO reviewByUserDTO) {}

}
