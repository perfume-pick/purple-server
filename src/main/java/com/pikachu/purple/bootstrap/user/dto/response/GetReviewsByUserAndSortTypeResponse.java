package com.pikachu.purple.bootstrap.user.dto.response;

import com.pikachu.purple.application.review.common.dto.ReviewWithPerfumeDTO;
import java.util.List;

public record GetReviewsByUserAndSortTypeResponse(List<ReviewWithPerfumeDTO> reviews) {

}
