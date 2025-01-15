package com.pikachu.purple.application.history.service.application.visithistory;

import static com.pikachu.purple.support.security.SecurityProvider.getCurrentUserAuthentication;

import com.pikachu.purple.application.history.common.dto.VisitHistoryDTO;
import com.pikachu.purple.application.history.port.in.visithistory.GetVisitHistoriesUseCase;
import com.pikachu.purple.application.history.service.domain.VisitHistoryDomainService;
import com.pikachu.purple.application.perfume.common.dto.PerfumeDTO;
import com.pikachu.purple.application.perfume.port.in.perfume.GetPerfumesByIdsUseCase;
import com.pikachu.purple.application.review.port.in.starrating.GetAverageScoreByPerfumeIdUseCase;
import com.pikachu.purple.domain.history.VisitHistory;
import com.pikachu.purple.domain.perfume.Perfume;
import java.util.List;
import java.util.stream.IntStream;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GetVisitHistoriesApplicationService implements GetVisitHistoriesUseCase {

    private final GetPerfumesByIdsUseCase getPerfumesByIdsUseCase;
    private final VisitHistoryDomainService visitHistoryDomainService;
    private final GetAverageScoreByPerfumeIdUseCase getAverageScoreByPerfumeIdUseCase;

    @Transactional
    @Override
    public Result invoke() {
        Long userId = getCurrentUserAuthentication().userId();

        List<VisitHistory> visitHistories = visitHistoryDomainService.findAllByUserId(userId);

        List<Long> perfumeIds = visitHistories.stream()
            .map(VisitHistory::getPerfumeId)
            .toList();

        GetPerfumesByIdsUseCase.Result result = getPerfumesByIdsUseCase.invoke(perfumeIds);
        for (Perfume perfume : result.perfumes()) {
            double averageScore = getAverageScoreByPerfumeIdUseCase.invoke(
                perfume.getId()).averageScore();

            perfume.setAverageScore(averageScore);
        }

        List<PerfumeDTO> perfumeDTOs = result.perfumes().stream()
            .map(PerfumeDTO::from)
            .toList();

        List<VisitHistoryDTO> visitHistoryDTOs = IntStream.range(0, perfumeDTOs.size())
            .mapToObj(i -> VisitHistoryDTO.of(
                i + 1,
                perfumeDTOs.get(i)
            ))
            .toList();

        return new Result(visitHistoryDTOs);
    }

}
