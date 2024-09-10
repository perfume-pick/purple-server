package com.pikachu.purple.application.perfume.service.application;

import com.pikachu.purple.application.perfume.common.dto.BrandPerfumesDTO;
import com.pikachu.purple.application.perfume.port.in.PerfumeGetByBrandsUseCase;
import com.pikachu.purple.application.perfume.service.domain.PerfumeDomainService;
import com.pikachu.purple.domain.perfume.Perfume;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PerfumeGetByBrandsApplicationService implements PerfumeGetByBrandsUseCase {

    private final PerfumeDomainService perfumeDomainService;

    @Override
    public Result invoke(Command command) {
        List<Perfume> perfumes = perfumeDomainService.findAllByPerfumeBrands(command.brands());

        Map<String, List<Perfume>> groupedByBrand = perfumes.stream()
            .collect(Collectors.groupingBy(Perfume::getBrandName));

        List<BrandPerfumesDTO> brandPerfumesDTOs = groupedByBrand.entrySet().stream()
            .map(entry -> BrandPerfumesDTO.of(
                entry.getKey(),
                    entry.getValue().stream()
                        .toList()
                )
            )
            .toList();

        return new Result(brandPerfumesDTOs);
    }

}
