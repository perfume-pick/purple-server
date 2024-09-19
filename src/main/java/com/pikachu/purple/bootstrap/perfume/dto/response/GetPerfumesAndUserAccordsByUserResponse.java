package com.pikachu.purple.bootstrap.perfume.dto.response;

import com.pikachu.purple.application.perfume.common.dto.RecommendedPerfumeDTO;
import com.pikachu.purple.domain.user.UserAccord;
import java.util.List;

public record GetPerfumesAndUserAccordsByUserResponse(
    List<UserAccord> userAccords,
    List<RecommendedPerfumeDTO> perfumes
) {}
