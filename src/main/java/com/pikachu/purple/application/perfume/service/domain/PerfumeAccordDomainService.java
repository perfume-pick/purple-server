package com.pikachu.purple.application.perfume.service.domain;

import com.pikachu.purple.domain.perfume.PerfumeAccord;

import java.util.List;

public interface PerfumeAccordDomainService {

    List<PerfumeAccord> findAllByPerfumeId(
        Long perfumeId,
        int maxSize
    );

}
