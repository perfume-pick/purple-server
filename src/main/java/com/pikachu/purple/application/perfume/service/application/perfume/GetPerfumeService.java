package com.pikachu.purple.application.perfume.service.application.perfume;

import com.pikachu.purple.application.perfume.port.in.perfume.GetPerfumeUseCase;
import com.pikachu.purple.application.perfume.service.domain.PerfumeDomainService;
import com.pikachu.purple.domain.perfume.Perfume;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class GetPerfumeService implements GetPerfumeUseCase {

    private final PerfumeDomainService perfumeDomainService;
    @Override
    public Result invoke(Long perfumeId) {
        Perfume perfume = perfumeDomainService.findById(perfumeId);

        return new Result(perfume);
    }

}
