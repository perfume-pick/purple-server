package com.pikachu.purple.bootstrap.recommend.dto.response;

import com.pikachu.purple.application.perfume.common.dto.RecommendedPerfumeDTO;
import java.util.List;

public record GetPerfumesByReviewCountsResponse(List<RecommendedPerfumeDTO> perfumes) {

}
