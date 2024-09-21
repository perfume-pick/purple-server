package com.pikachu.purple.application.perfume.port.in.brand;

import com.pikachu.purple.application.perfume.common.dto.BrandDTO;
import java.util.List;

public interface GetBrandsUseCase {

    Result invoke();

    record Result(List<BrandDTO> brandDTOs) {}

}
