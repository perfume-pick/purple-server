package com.pikachu.purple.application.perfume.service.application.perfume;

import com.pikachu.purple.application.perfume.port.in.perfume.GetPerfumeIdsUseCase;
import com.pikachu.purple.application.perfume.service.domain.PerfumeDomainService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class GetPerfumeIdsApplicationService implements GetPerfumeIdsUseCase {

    private final PerfumeDomainService perfumeDomainService;

    @Override
    public Result invoke() {
        List<Long> perfumeIds = perfumeDomainService.findAllId();

        return new Result(perfumeIds);
    }

}
