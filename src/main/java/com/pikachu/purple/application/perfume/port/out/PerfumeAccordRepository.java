package com.pikachu.purple.application.perfume.port.out;

import com.pikachu.purple.domain.perfume.Perfume;
import com.pikachu.purple.domain.perfume.PerfumeAccord;
import java.util.List;

public interface PerfumeAccordRepository {

    List<PerfumeAccord> findAll(Perfume perfume);

    List<PerfumeAccord> findAllByPerfumeIdOrderByValueDesc(
        Long perfumeId,
        int maxSize
    );

}
