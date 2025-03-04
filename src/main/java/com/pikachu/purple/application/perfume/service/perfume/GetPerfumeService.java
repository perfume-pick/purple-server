package com.pikachu.purple.application.perfume.service.perfume;

import com.pikachu.purple.application.perfume.port.in.perfume.GetPerfumeUseCase;
import com.pikachu.purple.application.perfume.port.out.PerfumeRepository;
import com.pikachu.purple.domain.perfume.Perfume;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
class GetPerfumeService implements GetPerfumeUseCase {

    private final PerfumeRepository perfumeRepository;

    @Transactional
    @Override
    public Result findByPerfumeId(Long perfumeId) {
        Perfume perfume = perfumeRepository.findByPerfumeId(perfumeId);

        return new Result(perfume);
    }

}
