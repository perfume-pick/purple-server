package com.pikachu.purple.bootstrap.perfume.dto.response;

import com.pikachu.purple.application.perfume.port.in.brand.GetBrandsUseCase;
import com.pikachu.purple.application.util.IdUtil;
import com.pikachu.purple.domain.perfume.Perfume;
import java.util.List;
import lombok.Getter;

@Getter
public class GetPerfumesByBrandNamesResponse {
    private final List<BrandPerfumesDTO> brands;

    public GetPerfumesByBrandNamesResponse(List<BrandPerfumesDTO> brands) {
        this.brands = brands;
    }

    public static GetPerfumesByBrandNamesResponse of(GetBrandsUseCase.Result result) {
        List<BrandPerfumesDTO> brands = result.brands().stream()
            .map(brand -> BrandPerfumesDTO.of(
                brand.getKoreanName(),
                brand.getPerfumes()
            ))
            .toList();

        return new GetPerfumesByBrandNamesResponse(brands);
    }

    record BrandPerfumesDTO(
        String brandName,
        List<PerfumeSimpleDTO> perfumes
    ) {
        public static BrandPerfumesDTO of(String brandName, List<Perfume> perfumes) {
            return new BrandPerfumesDTO(
                brandName,
                perfumes.stream()
                    .map(PerfumeSimpleDTO::from)
                    .toList()
            );
        }
    }

    record PerfumeSimpleDTO(
        String perfumeId,
        String name,
        String imageUrl,
        String brandName
    ) {
        public static PerfumeSimpleDTO from(Perfume perfume) {
            return new PerfumeSimpleDTO(
                IdUtil.toString(perfume.getId()),
                perfume.getKoreanName(),
                perfume.getImageUrl(),
                perfume.getBrand().getKoreanName()
            );
        }
    }

}
