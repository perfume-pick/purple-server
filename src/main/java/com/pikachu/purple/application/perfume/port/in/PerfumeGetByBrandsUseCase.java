package com.pikachu.purple.application.perfume.port.in;

import com.pikachu.purple.application.perfume.common.dto.BrandPerfumesDTO;
import java.util.List;

public interface PerfumeGetByBrandsUseCase {

    Result invoke(Command command);

    record Command(List<String> brands) {

    }

    record Result(List<BrandPerfumesDTO> brandPerfumesDTOs) {

    }

}
