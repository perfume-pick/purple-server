package com.pikachu.purple.application.perfume.port.out;

import com.pikachu.purple.domain.accord.Accord;
import com.pikachu.purple.domain.perfume.Perfume;
import java.util.List;

public interface PerfumeRepository {

    List<Perfume> findAllWithPerfumeAccordsByKeyword(String keyword);

    Perfume findById(Long perfumeId);

    List<Perfume> findAllByBrandNames(List<String> brandNames);

    List<Perfume> findAllWithPerfumeAccordsByAccords(List<Accord> accords);

    List<Perfume> findAllHavingReviewCountNotZeroOrderByReviewCount(int maxSize);

    List<Long> findAllId();

}
