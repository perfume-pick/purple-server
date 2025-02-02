package com.pikachu.purple.application.perfume.service.application.brand;

import com.pikachu.purple.application.perfume.common.dto.BrandDTO;
import com.pikachu.purple.application.perfume.common.dto.BrandPerfumesDTO;
import com.pikachu.purple.application.perfume.port.in.brand.GetBrandsUseCase;
import com.pikachu.purple.application.perfume.port.out.BrandRepository;
import com.pikachu.purple.application.perfume.service.domain.BrandDomainService;
import com.pikachu.purple.domain.perfume.Brand;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class GetBrandsService implements GetBrandsUseCase {

    private final BrandDomainService brandDomainService;
    private final BrandRepository brandRepository;

    @Override
    public Result findAll() {
        List<Brand> brands = brandDomainService.findAll();

        List<BrandDTO> brandDTOs = brands.stream()
            .map(BrandDTO::from)
            .toList();

        return new Result(brandDTOs);
    }

    @Override
    public ResultBrandPerfumesDTO findAllWithPerfumes(List<String> brandNames) {
        List<Brand> brands = brandRepository.findAllWithPerfumes(brandNames);

        return new ResultBrandPerfumesDTO(brands.stream()
            .map(brand -> BrandPerfumesDTO.of(
                brand.getKoreanName(),
                brand.getPerfumes())
            ).toList()
        );
    }

}
