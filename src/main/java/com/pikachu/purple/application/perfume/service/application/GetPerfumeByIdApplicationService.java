package com.pikachu.purple.application.perfume.service.application;

import com.pikachu.purple.application.perfume.port.in.GetPerfumeByIdUseCase;
import com.pikachu.purple.application.perfume.service.domain.PerfumeDomainService;
import com.pikachu.purple.domain.perfume.Perfume;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetPerfumeByIdApplicationService implements GetPerfumeByIdUseCase {

    private final PerfumeDomainService perfumeDomainService;

    @Override
    public Result invoke(Command command) {
        Perfume perfume = perfumeDomainService.findById(command.perfumeId());

        return new Result(perfume);
    }

}
