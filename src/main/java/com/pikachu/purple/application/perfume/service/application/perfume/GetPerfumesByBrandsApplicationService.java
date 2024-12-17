package com.pikachu.purple.application.perfume.service.application.perfume;

import com.pikachu.purple.application.perfume.common.dto.BrandPerfumesDTO;
import com.pikachu.purple.application.perfume.port.in.perfume.GetPerfumesByBrandsUseCase;
import com.pikachu.purple.application.perfume.service.domain.PerfumeDomainService;
import com.pikachu.purple.domain.perfume.Perfume;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetPerfumesByBrandsApplicationService implements GetPerfumesByBrandsUseCase {

    private final PerfumeDomainService perfumeDomainService;

    @Override
    public Result invoke(Command command) {
        List<Perfume> perfumes = perfumeDomainService.findAllByBrandNames(command.brandNames());

        Map<String, List<Perfume>> perfumesByBrand = perfumes.stream()
            .collect(Collectors.groupingBy(perfume -> perfume.getBrand().getKoreanName()));

        List<BrandPerfumesDTO> brandPerfumesDTOs = perfumesByBrand.entrySet().stream()
            .map(entry -> BrandPerfumesDTO.of(
                entry.getKey(),
                entry.getValue()
            )).toList();

        return new Result(brandPerfumesDTOs);
    }

}
