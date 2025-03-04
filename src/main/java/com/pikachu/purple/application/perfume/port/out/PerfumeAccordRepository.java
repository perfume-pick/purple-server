package com.pikachu.purple.application.perfume.port.out;

import com.pikachu.purple.domain.perfume.PerfumeAccord;
import java.util.List;

public interface PerfumeAccordRepository {

    List<PerfumeAccord> findAllPerfumeId(Long perfumeId);

    List<PerfumeAccord> findAllByPerfumeIdOrderByValueDesc(
        Long perfumeId,
        int maxSize
    );

}
