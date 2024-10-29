package com.pikachu.purple.application.perfume.service.application.perfume;

import com.pikachu.purple.application.perfume.common.dto.RecommendedPerfumeDTO;
import com.pikachu.purple.application.perfume.port.in.perfume.GetPerfumesByReviewCountUseCase;
import com.pikachu.purple.application.perfume.service.domain.PerfumeDomainService;
import com.pikachu.purple.application.review.port.in.starrating.GetAverageScoreByPerfumeIdUseCase;
import com.pikachu.purple.domain.perfume.Perfume;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GetPerfumesByReviewCountApplicationService implements
    GetPerfumesByReviewCountUseCase {

    private final PerfumeDomainService perfumeDomainService;
    private final GetAverageScoreByPerfumeIdUseCase getAverageScoreByPerfumeIdUseCase;

    private static final int MAX_SIZE = 30;

    @Transactional
    @Override
    public Result invoke() {
       List<Perfume> perfumes =  perfumeDomainService.findAllOrderByReviewCount(MAX_SIZE);
        for (Perfume perfume : perfumes) {
            double averageScore = getAverageScoreByPerfumeIdUseCase.invoke(
                new GetAverageScoreByPerfumeIdUseCase.Command(perfume.getId())).averageScore();
            perfume.setAverageScore(averageScore);
        }

       List<RecommendedPerfumeDTO> recommendedPerfumeDTOs = perfumes.stream()
           .map(perfume -> RecommendedPerfumeDTO.from(
               perfume,
               null
           ))
           .toList();

       return new Result(recommendedPerfumeDTOs);
    }

}
