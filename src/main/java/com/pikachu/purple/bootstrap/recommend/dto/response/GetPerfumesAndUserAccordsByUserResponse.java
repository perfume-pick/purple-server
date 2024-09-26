package com.pikachu.purple.bootstrap.recommend.dto.response;

import com.pikachu.purple.application.perfume.common.dto.RecommendedPerfumeDTO;
import com.pikachu.purple.application.perfume.common.dto.UserAccordDTO;
import java.util.List;

public record GetPerfumesAndUserAccordsByUserResponse(
    List<UserAccordDTO> userAccords,
    List<RecommendedPerfumeDTO> perfumes
) {}
