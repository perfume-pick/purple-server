package com.pikachu.purple.bootstrap.user.dto.response;

import com.pikachu.purple.application.review.common.dto.ReviewedBrandDTO;
import java.util.List;

public record GetTopThreeReviewedBrandsResponse(
    List<ReviewedBrandDTO> reviewedBrandDTOs
) {}
