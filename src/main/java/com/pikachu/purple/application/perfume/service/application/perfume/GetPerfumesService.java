package com.pikachu.purple.application.perfume.service.application.perfume;

import com.pikachu.purple.application.perfume.port.in.perfume.GetPerfumesUseCase;
import com.pikachu.purple.application.perfume.service.domain.PerfumeDomainService;
import com.pikachu.purple.application.review.port.in.starrating.GetAverageScoreByPerfumeIdUseCase;
import com.pikachu.purple.domain.perfume.Perfume;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class GetPerfumesService implements GetPerfumesUseCase {

    private final PerfumeDomainService perfumeDomainService;
    private final GetAverageScoreByPerfumeIdUseCase getAverageScoreByPerfumeIdUseCase;

    private static final int MAX_SIZE = 30;

    @Override
    public Result invoke(List<Long> perfumeIds) {

        return new Result(perfumeDomainService.findAllWithPerfumeAccordsByIds(perfumeIds));
    }

    //TODO -> List<PerfumeDTO>
    @Override
    @Transactional
    public Result invoke(String keyword) {
        List<Perfume> perfumes = perfumeDomainService.findAllWithPerfumeAccordsByKeyword(keyword);
        for (Perfume perfume : perfumes) {
            double averageScore = getAverageScoreByPerfumeIdUseCase.invoke(
                perfume.getId()).averageScore();
            perfume.setAverageScore(averageScore);
        }

        return new Result(perfumes);
    }

    //TODO -> List<RecommendedPerfumeDTO>
    @Override
    public Result invoke() {
        List<Perfume> perfumes =  perfumeDomainService.findAllOrderByReviewCount(MAX_SIZE);
        for (Perfume perfume : perfumes) {
            double averageScore = getAverageScoreByPerfumeIdUseCase.invoke(
                perfume.getId()).averageScore();
            perfume.setAverageScore(averageScore);
        }

        return new Result(perfumes);
    }

}
