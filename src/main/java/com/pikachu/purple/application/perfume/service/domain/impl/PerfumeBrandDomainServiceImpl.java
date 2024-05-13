package com.pikachu.purple.application.perfume.service.domain.impl;

import com.pikachu.purple.application.perfume.port.out.PerfumeBrandRepository;
import com.pikachu.purple.application.perfume.service.domain.PerfumeBrandDomainService;
import com.pikachu.purple.domain.perfume.PerfumeBrand;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class PerfumeBrandDomainServiceImpl implements PerfumeBrandDomainService {

    private final PerfumeBrandRepository perfumeBrandRepository;

    @Override
    public List<PerfumeBrand> findTopThirtyBrands() {
        return perfumeBrandRepository.findTopThirtyBrands();
    }

}
