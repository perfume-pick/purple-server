package com.pikachu.purple.application.perfume.service.domain;

import com.pikachu.purple.domain.accord.Accord;
import com.pikachu.purple.domain.perfume.Perfume;
import java.util.List;

public interface PerfumeDomainService {

    List<Perfume> findAllWithPerfumeAccordsByKeyword(String keyword);

    Perfume findById(Long perfumeId);

    List<Perfume> findAllByBrandNames(List<String> brandNames);

    List<Perfume> findAllByIds(List<Long> perfumeIds);

    List<Perfume> findAllWithPerfumeAccordsByAccords(List<Accord> accords);

}
