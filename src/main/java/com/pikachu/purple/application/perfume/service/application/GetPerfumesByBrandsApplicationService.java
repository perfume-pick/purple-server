package com.pikachu.purple.application.perfume.service.application;

import com.pikachu.purple.application.perfume.common.dto.BrandPerfumesDTO;
import com.pikachu.purple.application.perfume.port.in.GetPerfumesByBrandsUseCase;
import com.pikachu.purple.application.perfume.service.domain.BrandDomainService;
import com.pikachu.purple.domain.perfume.Brand;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetPerfumesByBrandsApplicationService implements GetPerfumesByBrandsUseCase {

    private final BrandDomainService brandDomainService;

    @Override
    public Result invoke(Command command) {
        List<Brand> brands = brandDomainService.findAllByBrandNames(command.brandNames());

        List<BrandPerfumesDTO> brandPerfumesDTOS = brands.stream()
            .map(BrandPerfumesDTO::from)
            .toList();

        return new Result(brandPerfumesDTOS);
    }

}
