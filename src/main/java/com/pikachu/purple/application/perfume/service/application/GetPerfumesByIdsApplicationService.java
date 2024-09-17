package com.pikachu.purple.application.perfume.service.application;

import com.pikachu.purple.application.perfume.port.in.GetPerfumesByIdsUseCase;
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
        List<Perfume> perfumes = perfumeDomainService.findAllByIds(command.perfumeIds());

        return new Result(perfumes);
    }

}