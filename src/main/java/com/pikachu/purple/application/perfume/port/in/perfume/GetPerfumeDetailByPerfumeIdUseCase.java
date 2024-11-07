package com.pikachu.purple.application.perfume.port.in.perfume;

import com.pikachu.purple.application.perfume.common.dto.PerfumeDetailDTO;

public interface GetPerfumeDetailByPerfumeIdUseCase {

    GetPerfumeDetailByPerfumeIdUseCase.Result invoke(
        GetPerfumeDetailByPerfumeIdUseCase.Command command);

    record Command(Long perfumeId) {}

    record Result(PerfumeDetailDTO perfumeDetail) {}

}
