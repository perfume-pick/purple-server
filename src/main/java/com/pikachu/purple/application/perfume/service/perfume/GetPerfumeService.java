package com.pikachu.purple.application.perfume.service.perfume;

import com.pikachu.purple.application.perfume.port.in.perfume.GetPerfumeUseCase;
import com.pikachu.purple.application.perfume.port.out.PerfumeRepository;
import com.pikachu.purple.domain.perfume.Perfume;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class GetPerfumeService implements GetPerfumeUseCase {

    private final PerfumeRepository perfumeRepository;
    @Override
    public Result invoke(Long perfumeId) {
        Perfume perfume = perfumeRepository.findById(perfumeId);

        return new Result(perfume);
    }

}
