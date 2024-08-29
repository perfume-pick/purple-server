package com.pikachu.purple.application.perfume.port.in;

import com.pikachu.purple.domain.perfume.PerfumeDetail;

public interface PerfumeDetailGetByPerfumeIdUseCase {

    PerfumeDetailGetByPerfumeIdUseCase.Result invoke(
        PerfumeDetailGetByPerfumeIdUseCase.Command command);

    record Command(Long perfumeId) {

    }

    record Result(PerfumeDetail perfumeDetail) {

    }

}
