package com.pikachu.purple.bootstrap.recommend.dto.response;

import com.pikachu.purple.domain.perfume.Perfume;
import java.util.List;

public record GetPerfumesByReviewCountsResponse(List<Perfume> perfumes) {

}
