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
public class PerfumeDomainServiceImpl implements PerfumeDomainService {

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
    public List<Perfume> findAllWithPerfumeAccordsByAccords(List<Accord> accords) {
        return perfumeRepository.findAllWithPerfumeAccordsByAccords(accords);
    }

    @Override
    public List<Perfume> findAllOrderByReviewCount(int maxSize) {
        return perfumeRepository.findAllHavingReviewCountNotZeroOrderByReviewCount(maxSize);
    }

    @Override
    public List<Perfume> findAllWithPerfumeAccordsByIds(List<Long> perfumeIds) {
        return perfumeRepository.findAllWithPerfumeAccordsByIds(perfumeIds);
    }

}
