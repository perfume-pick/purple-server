package com.pikachu.purple.application.perfume.service.application.perfume;

import com.pikachu.purple.application.perfume.common.dto.PerfumeDTO;
import com.pikachu.purple.application.perfume.port.in.perfume.GetPerfumesByKeywordUseCase;
import com.pikachu.purple.application.perfume.service.domain.PerfumeDomainService;
import com.pikachu.purple.application.review.port.in.starrating.GetAverageScoreByPerfumeIdUseCase;
import com.pikachu.purple.domain.perfume.Perfume;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetPerfumesByKeywordApplicationService implements GetPerfumesByKeywordUseCase {

    private final PerfumeDomainService perfumeDomainService;
    private final GetAverageScoreByPerfumeIdUseCase getAverageScoreByPerfumeIdUseCase;

    @Override
    @Transactional
    public Result invoke(Command command) {
        List<Perfume> perfumes = perfumeDomainService.findAllWithPerfumeAccordsByKeyword(command.keyword());
        for (Perfume perfume : perfumes) {
            double averageScore = getAverageScoreByPerfumeIdUseCase.invoke(
                new GetAverageScoreByPerfumeIdUseCase.Command(perfume.getId())).averageScore();
            perfume.setAverageScore(averageScore);
        }

        List<PerfumeDTO> perfumeDTOs = perfumes.stream()
            .map(PerfumeDTO::from)
            .toList();

        return new Result(perfumeDTOs);
    }

}
