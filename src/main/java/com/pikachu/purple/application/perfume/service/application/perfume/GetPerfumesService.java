package com.pikachu.purple.application.perfume.service.application.perfume;

import com.pikachu.purple.application.perfume.common.dto.PerfumeDTO;
import com.pikachu.purple.application.perfume.common.dto.RecommendedPerfumeDTO;
import com.pikachu.purple.application.perfume.port.in.perfume.GetPerfumesUseCase;
import com.pikachu.purple.application.perfume.service.domain.PerfumeDomainService;
import com.pikachu.purple.application.review.port.in.starrating.GetPerfumeAverageScoreUseCase;
import com.pikachu.purple.domain.perfume.Perfume;
import com.pikachu.purple.domain.perfume.PerfumeAccord;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class GetPerfumesService implements GetPerfumesUseCase {

    private final PerfumeDomainService perfumeDomainService;
    private final GetPerfumeAverageScoreUseCase getPerfumeAverageScoreUseCase;

    private static final int MAX_SIZE = 30;

    @Override
    public Result findAllWithPerfumeAccord(List<Long> perfumeIds) {

        return new Result(perfumeDomainService.findAllWithPerfumeAccordsByIds(perfumeIds));
    }

    @Override
    @Transactional
    public ResultPerfumeDTO findAllWithPerfumeAccord(String keyword) {
        List<Perfume> perfumes = perfumeDomainService.findAllWithPerfumeAccordsByKeyword(keyword);
        for (Perfume perfume : perfumes) {
            double averageScore = getPerfumeAverageScoreUseCase.find(
                perfume.getId()).averageScore();
            perfume.setAverageScore(averageScore);
        }

        List<PerfumeDTO> perfumeDTOs = perfumes.stream()
            .map(PerfumeDTO::from)
            .toList();

        return new ResultPerfumeDTO(perfumeDTOs);
    }

    @Override
    public ResultRecommendedPerfumeDTO findAllOrderByReviewCount() {
        List<Perfume> perfumes =  perfumeDomainService.findAllOrderByReviewCount(MAX_SIZE);
        for (Perfume perfume : perfumes) {
            double averageScore = getPerfumeAverageScoreUseCase.find(
                perfume.getId()).averageScore();
            perfume.setAverageScore(averageScore);
        }

        List<RecommendedPerfumeDTO> recommendedPerfumeDTOs = perfumes.stream()
            .map(perfume -> RecommendedPerfumeDTO.from(
                perfume,
                perfume.getAccords() == null ? List.of() : perfume.getAccords().stream()
                    .map(PerfumeAccord::getKoreanName)
                    .toList()
            ))
            .toList();

        return new ResultRecommendedPerfumeDTO(recommendedPerfumeDTOs);
    }

}
