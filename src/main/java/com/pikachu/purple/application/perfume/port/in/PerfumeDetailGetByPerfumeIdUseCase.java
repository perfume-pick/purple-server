package com.pikachu.purple.application.perfume.port.in;

import com.pikachu.purple.application.perfume.common.dto.PerfumeDetailDto;

public interface PerfumeDetailGetByPerfumeIdUseCase {

    PerfumeDetailGetByPerfumeIdUseCase.Result invoke(
        PerfumeDetailGetByPerfumeIdUseCase.Command command);

    record Command(Long perfumeId) {

    }

    record Result(PerfumeDetailDto perfumeDetail) {

    }

}
