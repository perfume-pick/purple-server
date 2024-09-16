package com.pikachu.purple.application.perfume.service.application;

import com.pikachu.purple.application.perfume.common.dto.BrandDTO;
import com.pikachu.purple.application.perfume.port.in.GetBrandsUseCase;
import com.pikachu.purple.application.perfume.service.domain.BrandDomainService;
import com.pikachu.purple.domain.perfume.Brand;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetBrandsApplicationService implements
    GetBrandsUseCase {

    private final BrandDomainService brandDomainService;

    @Override
    public GetBrandsUseCase.Result invoke() {
        List<Brand> brands = brandDomainService.findAll();

        List<BrandDTO> brandDTOs = brands.stream()
            .map(BrandDTO::from)
            .toList();

        return new GetBrandsUseCase.Result(brandDTOs);
    }

}
