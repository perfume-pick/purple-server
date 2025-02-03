package com.pikachu.purple.application.perfume.port.in.perfume;

import com.pikachu.purple.application.perfume.common.dto.PerfumeDTO;
import com.pikachu.purple.application.perfume.common.dto.RecommendedPerfumeDTO;
import com.pikachu.purple.domain.perfume.Perfume;
import java.util.List;

public interface GetPerfumesUseCase {

    Result findAllWithPerfumeAccord(List<Long> perfumeIds);

    ResultPerfumeDTO findAllWithPerfumeAccord(String keyword);

    ResultRecommendedPerfumeDTO findAllOrderByReviewCount();

    record Result(List<Perfume> perfumes) {}

    record ResultPerfumeDTO(List<PerfumeDTO> perfumeDTOs) {}

    record ResultRecommendedPerfumeDTO(List<RecommendedPerfumeDTO> perfumeDTOs) {}

}
