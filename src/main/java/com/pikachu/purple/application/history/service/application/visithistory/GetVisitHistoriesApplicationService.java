package com.pikachu.purple.application.history.service.application.visithistory;

import static com.pikachu.purple.support.security.SecurityProvider.getCurrentUserAuthentication;

import com.pikachu.purple.application.history.common.dto.VisitHistoryDTO;
import com.pikachu.purple.application.history.port.in.visithistory.GetVisitHistoriesUseCase;
import com.pikachu.purple.application.history.service.domain.VisitHistoryDomainService;
import com.pikachu.purple.application.perfume.common.dto.PerfumeDTO;
import com.pikachu.purple.application.perfume.port.in.perfume.GetPerfumesUseCase;
import com.pikachu.purple.application.review.port.in.starrating.GetPerfumeAverageScoreUseCase;
import com.pikachu.purple.domain.history.VisitHistory;
import com.pikachu.purple.domain.perfume.Perfume;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
class GetVisitHistoriesApplicationService implements GetVisitHistoriesUseCase {

    private final GetPerfumesUseCase getPerfumesUseCase;
    private final VisitHistoryDomainService visitHistoryDomainService;
    private final GetPerfumeAverageScoreUseCase getPerfumeAverageScoreUseCase;

    @Transactional
    @Override
    public Result invoke() {
        Long userId = getCurrentUserAuthentication().userId();

        List<VisitHistory> visitHistories = visitHistoryDomainService.findAllByUserId(userId);

        List<Long> perfumeIds = visitHistories.stream()
            .map(VisitHistory::getPerfumeId)
            .toList();

        List<Perfume> perfumes = getPerfumesUseCase.findAllWithPerfumeAccord(perfumeIds).perfumes();
        for (Perfume perfume : perfumes) {
            double averageScore = getPerfumeAverageScoreUseCase.find(
                perfume.getId()).averageScore();

            perfume.setAverageScore(averageScore);
        }

        Map<Long, PerfumeDTO> perfumeDTOMap = perfumes.stream()
            .collect(Collectors.toMap(
                Perfume::getId,
                PerfumeDTO::from
            ));

        List<PerfumeDTO> perfumeDTOs = perfumeIds.stream()
            .map(perfumeDTOMap::get)
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
