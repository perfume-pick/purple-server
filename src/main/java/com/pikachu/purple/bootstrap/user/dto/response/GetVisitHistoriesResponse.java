package com.pikachu.purple.bootstrap.user.dto.response;

import static com.pikachu.purple.bootstrap.common.exception.BusinessException.PerfumeAccordNotFoundException;

import com.pikachu.purple.application.perfume.port.in.GetVisitedPerfumesUseCase;
import com.pikachu.purple.application.util.IdUtil;
import com.pikachu.purple.domain.accord.enums.Accord;
import com.pikachu.purple.domain.perfume.Perfume;
import com.pikachu.purple.domain.perfume.PerfumeAccord;
import java.util.List;
import java.util.stream.IntStream;
import lombok.Getter;

@Getter
public class GetVisitHistoriesResponse{

    private final List<VisitHistoryDTO> perfumes;

    public GetVisitHistoriesResponse(List<VisitHistoryDTO> perfumes) {
        this.perfumes = perfumes;
    }

    public static GetVisitHistoriesResponse of(GetVisitedPerfumesUseCase.Result result) {
        List<VisitHistoryDTO> perfumes = IntStream.range(0, result.perfumes().size())
            .mapToObj(i -> VisitHistoryDTO.of(
                i + 1,
                PerfumeDTO.from(result.perfumes().get(i))
            ))
            .toList();

        return new GetVisitHistoriesResponse(perfumes);
    }

    record VisitHistoryDTO(
        int order,
        PerfumeDTO perfume
    ) {
        public static VisitHistoryDTO of(
            int order,
            PerfumeDTO perfume
        ) {
            return new VisitHistoryDTO(
                order,
                perfume
            );
        }
    }

    record PerfumeDTO(
        String perfumeId,
        String perfumeName,
        String brandName,
        String imageUrl,
        double averageScore,
        String accordName
    ) {
        public static PerfumeDTO from(Perfume perfume) {
            return new PerfumeDTO(
                IdUtil.toString(perfume.getId()),
                perfume.getKoreanName(),
                perfume.getBrand().getKoreanName(),
                perfume.getImageUrl(),
                perfume.getAverageScore(),
                perfume.getAccords().stream()
                    .map(PerfumeAccord::getAccord)
                    .map(Accord::getKoreanName)
                    .findFirst()
                    .orElseThrow(() -> PerfumeAccordNotFoundException)
            );
        }
    }

}
