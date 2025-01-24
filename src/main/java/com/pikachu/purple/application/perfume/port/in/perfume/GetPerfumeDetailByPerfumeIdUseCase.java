package com.pikachu.purple.application.perfume.port.in.perfume;

import com.pikachu.purple.application.perfume.common.dto.PerfumeDetailDTO;

public interface GetPerfumeDetailByPerfumeIdUseCase {

    Result invoke(Long perfumeId);

    record Result(PerfumeDetailDTO perfumeDetail) {}

}
