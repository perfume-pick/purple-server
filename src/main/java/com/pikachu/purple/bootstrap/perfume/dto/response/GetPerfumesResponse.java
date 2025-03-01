package com.pikachu.purple.bootstrap.perfume.dto.response;

import static com.pikachu.purple.bootstrap.common.exception.BusinessException.PerfumeAccordNotFoundException;

import com.pikachu.purple.application.perfume.port.in.perfume.GetPerfumesUseCase;
import com.pikachu.purple.application.util.IdUtil;
import com.pikachu.purple.domain.accord.Accord;
import com.pikachu.purple.domain.perfume.Perfume;
import java.util.List;
import lombok.Getter;

@Getter
public class GetPerfumesResponse {
    private final List<PerfumeDTO> perfumes;

    public GetPerfumesResponse(List<PerfumeDTO> perfumes) {
        this.perfumes = perfumes;
    }

    public static GetPerfumesResponse of(GetPerfumesUseCase.Result result) {
        List<PerfumeDTO> perfumes = result.perfumes().stream()
            .map(PerfumeDTO::from)
            .toList();

        return new GetPerfumesResponse(perfumes);
    }

    record PerfumeDTO(
        String perfumeId,
        String perfumeName,
        String brandName,
        String imageUrl,
        double averageScore,
        String accordName
    ) {

        public static PerfumeDTO from(
            Perfume perfume) {
            return new PerfumeDTO(
                IdUtil.toString(perfume.getId()),
                perfume.getKoreanName(),
                perfume.getBrand().getKoreanName(),
                perfume.getImageUrl(),
                perfume.getAverageScore(),
                perfume.getAccords().stream()
                    .map(Accord::getKoreanName)
                    .findFirst()
                    .orElseThrow(() -> PerfumeAccordNotFoundException)
            );
        }
    }

}
