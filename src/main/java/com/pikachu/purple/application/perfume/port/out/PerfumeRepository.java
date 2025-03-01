package com.pikachu.purple.application.perfume.port.out;

import com.pikachu.purple.application.perfume.port.in.perfume.GetPerfumesUseCase.Result;
import com.pikachu.purple.domain.accord.Accord;
import com.pikachu.purple.domain.perfume.Brand;
import com.pikachu.purple.domain.perfume.Perfume;
import java.util.List;

public interface PerfumeRepository {

    List<Perfume> findAll(Brand brand);

    List<Perfume> findAllWithPerfumeAccordsByKeyword(String keyword);

    Perfume findById(Long perfumeId);

    List<Perfume> findAllWithPerfumeAccordsByAccords(List<Accord> accords, int maxSize);

    List<Perfume> findAllHavingReviewCountNotZeroOrderByReviewCount(int maxSize);

    List<Long> findAllId();

    List<Perfume> findAllWithPerfumeAccordsByIds(List<Long> perfumeIds);

    void updateAverageScore(Long perfumeId, double averageScore);

    void updateAllAverageScore(List<Perfume> perfumes);

}
