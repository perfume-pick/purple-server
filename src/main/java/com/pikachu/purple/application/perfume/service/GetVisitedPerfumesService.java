package com.pikachu.purple.application.perfume.service;

import com.pikachu.purple.application.history.port.in.visithistory.GetVisitHistoriesUseCase;
import com.pikachu.purple.application.perfume.port.in.GetVisitedPerfumesUseCase;
import com.pikachu.purple.application.perfume.port.in.perfume.GetPerfumesUseCase;
import com.pikachu.purple.application.review.port.in.starrating.GetPerfumeAverageScoreUseCase;
import com.pikachu.purple.domain.history.VisitHistory;
import com.pikachu.purple.domain.perfume.Perfume;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
class GetVisitedPerfumesService implements GetVisitedPerfumesUseCase {

    private final GetVisitHistoriesUseCase getVisitHistoriesUseCase;
    private final GetPerfumesUseCase getPerfumesUseCase;
    private final GetPerfumeAverageScoreUseCase getPerfumeAverageScoreUseCase;

    @Transactional
    @Override
    public Result findAllByUserIdWithPerfumeAccord(Long userId) {
        List<VisitHistory> visitHistories = getVisitHistoriesUseCase.findAllUserId(userId).visitHistories();
        List<Long> perfumeIds = visitHistories.stream()
            .map(VisitHistory::getPerfumeId)
            .toList();

        List<Perfume> perfumes = getPerfumesUseCase.findAllWithPerfumeAccord(perfumeIds).perfumes();
        for (Perfume perfume : perfumes) {
            double averageScore = getPerfumeAverageScoreUseCase.findByPerfumeId(
                perfume.getId()).averageScore();

            perfume.setAverageScore(averageScore);
        }

        Map<Long, Perfume> perfumeMap = perfumes.stream()
            .collect(Collectors.toMap(Perfume::getId, p -> p));

        List<Perfume> sortedPerfumes = perfumeIds.stream()
            .map(perfumeMap::get)
            .toList();

        return new Result(sortedPerfumes);
    }

}
