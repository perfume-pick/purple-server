package com.pikachu.purple.bootstrap.recommend.dto.response;

import com.pikachu.purple.application.perfume.port.in.perfume.GetPerfumesUseCase;
import com.pikachu.purple.application.util.IdUtil;
import com.pikachu.purple.domain.perfume.Perfume;
import com.pikachu.purple.domain.perfume.PerfumeAccord;
import java.util.List;


public class GetPerfumesByReviewCountsResponse {
    private final List<RecommendedPerfumeDTO> perfumes;

    public GetPerfumesByReviewCountsResponse(List<RecommendedPerfumeDTO> perfumes) {
        this.perfumes = perfumes;
    }

    public static GetPerfumesByReviewCountsResponse of(GetPerfumesUseCase.Result result) {
        List<RecommendedPerfumeDTO> perfumes = result.perfumes().stream()
            .map(perfume -> RecommendedPerfumeDTO.from(
                perfume,
                perfume.getAccords() == null ? List.of() : perfume.getAccords().stream()
                    .map(PerfumeAccord::getKoreanName)
                    .toList()
            ))
            .toList();
        return new GetPerfumesByReviewCountsResponse(perfumes);
    }

    record RecommendedPerfumeDTO(
        String perfumeId,
        String name,
        String brandName,
        String imageUrl,
        double averageScore,
        List<String> accordNames
    ) {

        public static RecommendedPerfumeDTO from(
            Perfume perfume,
            List<String> accordNames
        ) {
            return new RecommendedPerfumeDTO(
                IdUtil.toString(perfume.getId()),
                perfume.getKoreanName(),
                perfume.getBrand().getKoreanName(),
                perfume.getImageUrl(),
                perfume.getAverageScore(),
                accordNames
            );
        }
    }

}
