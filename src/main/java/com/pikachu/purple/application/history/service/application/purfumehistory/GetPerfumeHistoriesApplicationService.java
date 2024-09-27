package com.pikachu.purple.application.history.service.application.purfumehistory;

import static com.pikachu.purple.bootstrap.common.exception.BusinessException.PerfumeAccordNotFoundException;
import static com.pikachu.purple.support.security.SecurityProvider.getCurrentUserAuthentication;

import com.pikachu.purple.application.history.common.dto.PerfumeHistoryDTO;
import com.pikachu.purple.application.history.port.in.perfumehistory.GetPerfumeHistoriesUseCase;
import com.pikachu.purple.application.history.service.domain.PerfumeHistoryDomainService;
import com.pikachu.purple.application.perfume.common.dto.PerfumeDTO;
import com.pikachu.purple.application.perfume.port.in.perfume.GetPerfumesByIdsUseCase;
import com.pikachu.purple.application.perfume.port.in.perfume.GetPerfumesByIdsUseCase.Command;
import com.pikachu.purple.domain.accord.Accord;
import com.pikachu.purple.domain.history.PerfumeHistory;
import java.util.List;
import java.util.stream.IntStream;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GetPerfumeHistoriesApplicationService implements GetPerfumeHistoriesUseCase {

    private final GetPerfumesByIdsUseCase getPerfumesByIdsUseCase;
    private final PerfumeHistoryDomainService perfumeHistoryDomainService;

    @Transactional
    @Override
    public Result invoke() {
        Long userId = getCurrentUserAuthentication().userId();

        List<PerfumeHistory> perfumeHistories = perfumeHistoryDomainService.findAllByUserId(userId);

        List<Long> perfumeIds = perfumeHistories.stream()
            .map(PerfumeHistory::getPerfumeId)
            .toList();

        GetPerfumesByIdsUseCase.Result result = getPerfumesByIdsUseCase.invoke(new Command(perfumeIds));

        List<PerfumeDTO> perfumeDTOs = result.perfumes().stream()
            .map(perfume -> PerfumeDTO.from(
                perfume,
                perfume.getAccords().stream()
                    .map(Accord::getName)
                    .findFirst()
                    .orElseThrow(() -> PerfumeAccordNotFoundException)
            ))
            .toList();

        List<PerfumeHistoryDTO> perfumeHistoryDTOs = IntStream.range(0, perfumeDTOs.size())
            .mapToObj(i -> PerfumeHistoryDTO.of(
                i + 1,
                perfumeDTOs.get(i)
            ))
            .toList();

        return new Result(perfumeHistoryDTOs);
    }

}
