package com.pikachu.purple.application.perfume.service.domain.impl;

import com.pikachu.purple.application.perfume.port.out.PerfumeAccordRepository;
import com.pikachu.purple.application.perfume.service.domain.PerfumeAccordDomainService;
import com.pikachu.purple.domain.perfume.PerfumeAccord;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PerfumeAccordDomainServiceImpl implements PerfumeAccordDomainService {

    private final PerfumeAccordRepository perfumeAccordRepository;

    @Override
    public List<PerfumeAccord> findAllByPerfumeIdOrderByValueDesc(
        Long perfumeId,
        int maxSize
    ) {
        return perfumeAccordRepository.findAllByPerfumeIdOrderByValueDesc(
            perfumeId,
            maxSize
        );
    }

}
