package com.pikachu.purple.application.perfume.port.in;

import com.pikachu.purple.application.perfume.common.dto.RecommendedPerfumeDTO;
import com.pikachu.purple.application.perfume.common.dto.UserAccordDTO;
import java.util.List;

public interface GetRecommendedPerfumesByUserAccordsUseCase {

    Result findAll(Long userId);


    record Result(
        List<UserAccordDTO> userAccords,
        List<RecommendedPerfumeDTO> recommendedPerfumeDTOs
    ) {}

}
