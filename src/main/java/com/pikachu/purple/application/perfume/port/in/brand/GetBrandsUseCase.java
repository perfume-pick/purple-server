package com.pikachu.purple.application.perfume.port.in.brand;

import com.pikachu.purple.application.perfume.common.dto.BrandDTO;
import com.pikachu.purple.application.perfume.common.dto.BrandPerfumesDTO;
import java.util.List;

public interface GetBrandsUseCase {

    Result findAll();

    ResultBrandPerfumesDTO findAllWithPerfumes(List<String> brandNames);

    record Result(List<BrandDTO> data) {}

    record ResultBrandPerfumesDTO(List<BrandPerfumesDTO> data) {}

}
