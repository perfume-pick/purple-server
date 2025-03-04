package com.pikachu.purple.application.perfume.service.perfumeaccord;

import com.pikachu.purple.application.perfume.port.in.perfumeaccord.GetPerfumeAccordsUseCase;
import com.pikachu.purple.application.perfume.port.out.PerfumeAccordRepository;
import com.pikachu.purple.domain.perfume.Perfume;
import com.pikachu.purple.domain.perfume.PerfumeAccord;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GetPerfumeAccordsService implements GetPerfumeAccordsUseCase {

    private final PerfumeAccordRepository perfumeAccordRepository;

    @Transactional
    @Override
    public Result findAll(Perfume perfume) {

        List<PerfumeAccord> perfumeAccords = perfumeAccordRepository.findAllPerfumeId(
            perfume.getId());

        return new Result(perfumeAccords);
    }

}
