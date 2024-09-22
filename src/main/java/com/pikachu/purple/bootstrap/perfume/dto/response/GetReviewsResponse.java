package com.pikachu.purple.bootstrap.perfume.dto.response;

import com.pikachu.purple.application.review.common.dto.ReviewDTO;
import java.util.List;

public record GetReviewsResponse(List<ReviewDTO> reviews) {}
