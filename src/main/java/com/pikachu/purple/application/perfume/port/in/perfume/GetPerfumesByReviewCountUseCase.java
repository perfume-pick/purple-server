package com.pikachu.purple.application.perfume.port.in.perfume;

import com.pikachu.purple.application.perfume.common.dto.RecommendedPerfumeDTO;
import java.util.List;

public interface GetPerfumesByReviewCountUseCase {

    Result invoke();

    record Result(List<RecommendedPerfumeDTO> perfumeDTOs) {}

}
