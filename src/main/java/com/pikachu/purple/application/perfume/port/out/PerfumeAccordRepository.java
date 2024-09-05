package com.pikachu.purple.application.perfume.port.out;

import com.pikachu.purple.domain.perfume.PerfumeAccord;

import java.util.List;

public interface PerfumeAccordRepository {

    List<PerfumeAccord> findAllByPerfumeIdOrderByAccordValueDesc(
        Long perfumeId,
        int maxSize
    );

}
