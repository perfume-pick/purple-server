package com.pikachu.purple.bootstrap.perfume.dto.response;

import com.pikachu.purple.application.perfume.port.in.brand.GetBrandsUseCase;
import com.pikachu.purple.domain.perfume.Brand;
import java.util.List;
import lombok.Getter;

@Getter
public class GetBrandsResponse {
    private final List<BrandDTO> brands;

    public GetBrandsResponse(List<BrandDTO> brands) {
        this.brands = brands;
    }

    public static GetBrandsResponse of(GetBrandsUseCase.Result result) {
        List<BrandDTO> brands = result.brands().stream()
            .map(BrandDTO::from)
            .toList();

        return new GetBrandsResponse(brands);
    }

    record BrandDTO(
        String name,
        String koreanName,
        String imageUrl,
        int order
    ) {
        public static BrandDTO from(Brand brand) {
            return new BrandDTO(
                brand.getName(),
                brand.getKoreanName(),
                brand.getImageUrl(),
                brand.getOrder()
            );
        }

    }

}
