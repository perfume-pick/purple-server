package com.pikachu.purple.application.perfume.service.application;

import com.pikachu.purple.application.perfume.port.in.PerfumeCreateUseCase;
import com.pikachu.purple.application.perfume.service.domain.PerfumeDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PerfumeCreateApplicationService implements PerfumeCreateUseCase {

    private final PerfumeDomainService perfumeDomainService;

    @Override
    public void invoke(Command command) {
        perfumeDomainService.create(
            command.perfumeId(),
            command.perfumeName(),
            command.brandName()
        );
    }

}
