package com.pikachu.purple.application.perfume.service.application;

import com.pikachu.purple.application.perfume.port.in.PerfumeBrandGetTopThirtyUseCase;
import com.pikachu.purple.application.perfume.service.domain.PerfumeBrandDomainService;
import com.pikachu.purple.domain.perfume.PerfumeBrand;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PerfumeBrandGetTopThirtyApplicationService implements
    PerfumeBrandGetTopThirtyUseCase {

    private final PerfumeBrandDomainService perfumeBrandDomainService;

    @Override
    public PerfumeBrandGetTopThirtyUseCase.Result invoke() {
        List<PerfumeBrand> perfumeBrandList = perfumeBrandDomainService.findTopThirtyBrands();

        return new PerfumeBrandGetTopThirtyUseCase.Result(perfumeBrandList);
    }

}
