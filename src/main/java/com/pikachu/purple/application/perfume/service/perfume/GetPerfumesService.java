package com.pikachu.purple.application.perfume.service.perfume;

import com.pikachu.purple.application.perfume.port.in.perfume.GetPerfumesUseCase;
import com.pikachu.purple.application.perfume.port.in.perfumeaccord.GetPerfumeAccordsUseCase;
import com.pikachu.purple.application.perfume.port.out.PerfumeRepository;
import com.pikachu.purple.application.review.port.in.starrating.GetPerfumeAverageScoreUseCase;
import com.pikachu.purple.domain.perfume.Brand;
import com.pikachu.purple.domain.perfume.Perfume;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
class GetPerfumesService implements GetPerfumesUseCase {

    private final GetPerfumeAverageScoreUseCase getPerfumeAverageScoreUseCase;
    private final GetPerfumeAccordsUseCase getPerfumeAccordsUseCase;

    private final PerfumeRepository perfumeRepository;

    private static final int MAX_SIZE = 30;

    @Transactional
    @Override
    public Result findAll(Brand brand) {
        List<Perfume> perfumes = perfumeRepository.findAll(brand);

        return new Result(perfumes);
    }

    @Transactional
    @Override
    public Result findAllWithPerfumeAccord(List<Long> perfumeIds) {
        List<Perfume> perfumes = perfumeRepository.findAll(perfumeIds);
        this.setPerfumeAccords(perfumes);

        return new Result(perfumes);
    }

    @Transactional
    @Override
    public Result findAllWithPerfumeAccord(String keyword) {
        List<Perfume> perfumes = perfumeRepository.findAll(keyword);

        this.setPerfumeAccords(perfumes);
        this.setAverageScore(perfumes);

        return new Result(perfumes);
    }

    @Transactional
    @Override
    public Result findAllWithPerfumeAccordOrderByReviewCount() {
        List<Perfume> perfumes =  perfumeRepository.findAllHavingReviewCountNotZeroOrderByReviewCount(MAX_SIZE);

        this.setPerfumeAccords(perfumes);
        this.setAverageScore(perfumes);

        return new Result(perfumes);
    }

    private void setPerfumeAccords(List<Perfume> perfumes) {
        for (Perfume perfume : perfumes) {
            perfume.setAccords(
                getPerfumeAccordsUseCase
                    .findAll(perfume)
                    .perfumeAccords()
            );
        }
    }

    private void setAverageScore(List<Perfume> perfumes) {
        for (Perfume perfume : perfumes) {
            double averageScore = getPerfumeAverageScoreUseCase.findByPerfumeId(
                perfume.getId()).averageScore();
            perfume.setAverageScore(averageScore);
        }
    }

}
