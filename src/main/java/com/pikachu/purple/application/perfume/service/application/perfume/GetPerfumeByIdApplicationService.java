package com.pikachu.purple.application.perfume.service.application.perfume;

import com.pikachu.purple.application.perfume.port.in.perfume.GetPerfumeByIdUseCase;
import com.pikachu.purple.application.perfume.service.domain.PerfumeDomainService;
import com.pikachu.purple.domain.perfume.Perfume;
import com.pikachu.purple.infrastructure.persistence.perfume.adaptor.PerfumeAccordJpaAdaptor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class GetPerfumeByIdApplicationService implements GetPerfumeByIdUseCase {

    private final PerfumeDomainService perfumeDomainService;
    private final PerfumeAccordJpaAdaptor perfumeAccordJpaAdaptor;

    @Override
    public Result invoke(Long perfumeId) {
        perfumeAccordJpaAdaptor.findAllByPerfumeIdOrderByValueDesc(null, 0);
        Perfume perfume = perfumeDomainService.findById(perfumeId);

        return new Result(perfume);
    }

}
