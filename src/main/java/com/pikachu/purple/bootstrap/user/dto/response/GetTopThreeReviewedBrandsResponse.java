package com.pikachu.purple.bootstrap.user.dto.response;

import com.pikachu.purple.application.review.port.in.review.GetTopThreeReviewedBrandsUseCase.Result;
import com.pikachu.purple.application.review.port.in.review.GetTopThreeReviewedBrandsUseCase.ReviewedBrandDTO;
import java.util.List;
import lombok.Getter;

@Getter
public class GetTopThreeReviewedBrandsResponse {

    List<ReviewedBrandDTO> reviewedBrandDTOs;

    private GetTopThreeReviewedBrandsResponse(List<ReviewedBrandDTO> reviewedBrandDTOs) {
        this.reviewedBrandDTOs = reviewedBrandDTOs;
    }

    public static GetTopThreeReviewedBrandsResponse of(Result result) {
        return new GetTopThreeReviewedBrandsResponse(result.reviewedBrandDTOs());
    }

}
