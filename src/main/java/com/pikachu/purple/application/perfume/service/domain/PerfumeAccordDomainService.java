package com.pikachu.purple.application.perfume.service.domain;

import com.pikachu.purple.domain.perfume.PerfumeAccord;

import java.util.List;

public interface PerfumeAccordDomainService {

    List<PerfumeAccord> findAllByPerfumeIdOrderByValueDesc(
        Long perfumeId,
        int maxSize
    );

}
