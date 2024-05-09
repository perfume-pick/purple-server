package com.pikachu.purple.application.perfume.service.application;

import com.pikachu.purple.application.perfume.port.in.PerfumeBrandGetUseCase;
import com.pikachu.purple.application.perfume.service.domain.PerfumeBrandDomainService;
import com.pikachu.purple.domain.perfume.PerfumeBrand;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PerfumeBrandGetService implements PerfumeBrandGetUseCase {

    private final PerfumeBrandDomainService perfumeBrandDomainService;

    @Override
    public PerfumeBrandGetUseCase.Result invoke() {
        List<PerfumeBrand> perfumeBrandList = perfumeBrandDomainService.findAll();

        return new PerfumeBrandGetUseCase.Result(perfumeBrandList);
    }

}
