package com.pikachu.purple.application.perfume.port.out;

import com.pikachu.purple.domain.accord.Accord;
import com.pikachu.purple.domain.perfume.Perfume;
import java.util.List;

public interface PerfumeRepository {

    List<Perfume> findAllWithPerfumeAccordsByKeyword(String keyword);

    Perfume findById(Long perfumeId);

    List<Perfume> findAllByBrandNames(List<String> brandNames);

    List<Perfume> findAllWithPerfumeAccordsByAccords(List<Accord> accords, int maxSize);

    List<Perfume> findAllHavingReviewCountNotZeroOrderByReviewCount(int maxSize);

    List<Long> findAllId();

    List<Perfume> findAllWithPerfumeAccordsByIds(List<Long> perfumeIds);

}
