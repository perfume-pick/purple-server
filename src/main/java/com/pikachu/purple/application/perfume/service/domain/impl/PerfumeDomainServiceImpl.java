package com.pikachu.purple.application.perfume.service.domain.impl;

import com.pikachu.purple.application.perfume.port.out.PerfumeRepository;
import com.pikachu.purple.application.perfume.service.domain.PerfumeDomainService;
import com.pikachu.purple.domain.accord.Accord;
import com.pikachu.purple.domain.perfume.Perfume;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class PerfumeDomainServiceImpl implements PerfumeDomainService {

    private final PerfumeRepository perfumeRepository;

    @Override
    public List<Perfume> findAllWithPerfumeAccordsByKeyword(String keyword) {
        return perfumeRepository.findAllWithPerfumeAccordsByKeyword("%" + keyword + "%");
    }

    @Override
    public Perfume findById(Long perfumeId) {
        return perfumeRepository.findById(perfumeId);
    }

    @Override
    public List<Perfume> findAllByBrandNames(List<String> brandNames) {
        return perfumeRepository.findAllByBrandNames(brandNames);
    }

    @Override
    public List<Perfume> findAllWithPerfumeAccordsByAccords(
        List<Accord> accords,
        int maxSize
    ) {
        return perfumeRepository.findAllWithPerfumeAccordsByAccords(
            accords,
            maxSize
        );
    }

    @Override
    public List<Perfume> findAllOrderByReviewCount(int maxSize) {
        return perfumeRepository.findAllHavingReviewCountNotZeroOrderByReviewCount(maxSize);
    }

    @Override
    public List<Long> findAllId() {
        return perfumeRepository.findAllId();
    }

    @Override
    public List<Perfume> findAllWithPerfumeAccordsByIds(List<Long> perfumeIds) {
        return perfumeRepository.findAllWithPerfumeAccordsByIds(perfumeIds);
    }

    @Override
    public void updateAverageScore(
        Long perfumeId,
        double averageScore
    ) {
        perfumeRepository.updateAverageScore(
            perfumeId,
            averageScore
        );
    }

    @Override
    public void updateAllAverageScore(List<Perfume> perfumes) {
        for (Perfume perfume : perfumes) {
            perfumeRepository.updateAverageScore(
                perfume.getId(),
                perfume.getAverageScore()
            );
        }
    }

}
