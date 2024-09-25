package com.pikachu.purple.application.perfume.service.application.perfume;

import com.pikachu.purple.application.perfume.common.dto.RecommendedPerfumeDTO;
import com.pikachu.purple.application.perfume.port.in.perfume.GetPerfumesByReviewCountsUseCase;
import com.pikachu.purple.application.perfume.service.domain.PerfumeDomainService;
import com.pikachu.purple.domain.perfume.Perfume;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GetPerfumesByReviewCountsApplicationService implements
    GetPerfumesByReviewCountsUseCase {

    private final PerfumeDomainService perfumeDomainService;

    @Transactional
    @Override
    public Result invoke() {
       List<Perfume> perfumes =  perfumeDomainService.findAllOrderByReviewCounts();

       List<RecommendedPerfumeDTO> recommendedPerfumeDTOs = perfumes.stream()
           .map(perfume -> RecommendedPerfumeDTO.from(
               perfume,
               null
           ))
           .toList();

       return new Result(recommendedPerfumeDTOs);
    }

}
