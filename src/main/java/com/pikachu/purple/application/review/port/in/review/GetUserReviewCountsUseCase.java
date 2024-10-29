package com.pikachu.purple.application.review.port.in.review;


import com.pikachu.purple.application.review.common.dto.UserReviewCountsDTO;

public interface GetUserReviewCountsUseCase {

    Result invoke();

    record Result(UserReviewCountsDTO userReviewCountsDTO) {}

}
