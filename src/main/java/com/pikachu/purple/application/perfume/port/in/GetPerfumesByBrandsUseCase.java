package com.pikachu.purple.application.perfume.port.in;

import com.pikachu.purple.application.perfume.common.dto.BrandPerfumesDTO;
import java.util.List;

public interface GetPerfumesByBrandsUseCase {

    Result invoke(Command command);

    record Command(List<String> brandNames) {}

    record Result(List<BrandPerfumesDTO> brandPerfumesDTOs) {}

}
