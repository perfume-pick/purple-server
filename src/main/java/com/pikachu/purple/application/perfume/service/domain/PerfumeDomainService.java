package com.pikachu.purple.application.perfume.service.domain;

import com.pikachu.purple.domain.accord.Accord;
import com.pikachu.purple.domain.perfume.Perfume;
import java.util.List;

public interface PerfumeDomainService {

    List<Perfume> findAllWithPerfumeAccordsByKeyword(String keyword);

    Perfume findById(Long perfumeId);

    List<Perfume> findAllByBrandNames(List<String> brandNames);

    List<Perfume> findAllWithPerfumeAccordsByAccords(
        List<Accord> accords,
        int maxSize
    );

    List<Perfume> findAllOrderByReviewCount(int maxSize);

    List<Long> findAllId();

    List<Perfume> findAllWithPerfumeAccordsByIds(List<Long> longs);

    void updateAverageScore(
        Long perfumeId,
        double averageScore
    );

    void updateAllAverageScore(List<Perfume> perfumes);

}
