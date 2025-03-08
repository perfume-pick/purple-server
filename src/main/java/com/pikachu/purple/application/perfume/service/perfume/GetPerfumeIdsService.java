package com.pikachu.purple.application.perfume.service.perfume;

import com.pikachu.purple.application.perfume.port.in.perfume.GetPerfumeIdsUseCase;
import com.pikachu.purple.application.perfume.port.out.PerfumeRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
class GetPerfumeIdsService implements GetPerfumeIdsUseCase {

    private final PerfumeRepository perfumeRepository;

    @Override
    public Result invoke() {
        List<Long> perfumeIds = perfumeRepository.findAllId();

        return new Result(perfumeIds);
    }

}
