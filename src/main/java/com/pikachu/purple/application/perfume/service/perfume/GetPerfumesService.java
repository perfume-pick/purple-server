package com.pikachu.purple.application.perfume.service.perfume;

import com.pikachu.purple.application.perfume.port.in.perfume.GetPerfumesUseCase;
import com.pikachu.purple.application.perfume.port.out.PerfumeRepository;
import com.pikachu.purple.application.review.port.in.starrating.GetPerfumeAverageScoreUseCase;
import com.pikachu.purple.domain.perfume.Brand;
import com.pikachu.purple.domain.perfume.Perfume;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class GetPerfumesService implements GetPerfumesUseCase {

    private final PerfumeRepository perfumeRepository;
    private final GetPerfumeAverageScoreUseCase getPerfumeAverageScoreUseCase;

    private static final int MAX_SIZE = 30;

    @Override
    public Result findAll(Brand brand) {
        List<Perfume> perfumes = perfumeRepository.findAll(brand);

        return new Result(perfumes);
    }

    @Override
    public Result findAllWithPerfumeAccord(List<Long> perfumeIds) {
        List<Perfume> perfumes = perfumeRepository.findAllWithPerfumeAccordsByIds(perfumeIds);

        return new Result(perfumes);
    }

    @Override
    @Transactional
    public Result findAllWithPerfumeAccord(String keyword) {
        List<Perfume> perfumes = perfumeRepository.findAllWithPerfumeAccordsByKeyword(keyword);
        setAverageScore(perfumes);

        return new Result(perfumes);
    }

    @Override
    public Result findAllOrderByReviewCount() {
        List<Perfume> perfumes =  perfumeRepository.findAllHavingReviewCountNotZeroOrderByReviewCount(MAX_SIZE);
        setAverageScore(perfumes);

        return new Result(perfumes);
    }

    private void setAverageScore(List<Perfume> perfumes) {
        for (Perfume perfume : perfumes) {
            double averageScore = getPerfumeAverageScoreUseCase.find(
                perfume.getId()).averageScore();
            perfume.setAverageScore(averageScore);
        }
    }

}
