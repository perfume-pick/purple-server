package com.pikachu.purple.application.perfume.service.application.perfume;

import com.pikachu.purple.application.perfume.port.in.perfume.GetPerfumesByIdsUseCase;
import com.pikachu.purple.application.perfume.service.domain.PerfumeDomainService;
import com.pikachu.purple.domain.perfume.Perfume;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetPerfumesByIdsApplicationService implements GetPerfumesByIdsUseCase {

    private final PerfumeDomainService perfumeDomainService;

    @Override
    public Result invoke(Command command) {
        List<Perfume> perfumes = perfumeDomainService.findAllWithPerfumeAccordsByIds(command.perfumeIds());

        return new Result(perfumes);
    }

}
