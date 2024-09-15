package com.pikachu.purple.application.perfume.service.application;

import com.pikachu.purple.application.perfume.common.dto.PerfumeDTO;
import com.pikachu.purple.application.perfume.port.in.PerfumeGetByKeywordUseCase;
import com.pikachu.purple.application.perfume.service.domain.PerfumeDomainService;
import com.pikachu.purple.domain.perfume.Perfume;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PerfumeGetByKeywordApplicationService implements PerfumeGetByKeywordUseCase {

    private final PerfumeDomainService perfumeDomainService;

    @Override
    public Result invoke(Command command) {
        List<Perfume> perfumes = perfumeDomainService.findByKeyword(command.keyword());

        return new Result(perfumes.stream()
            .map(PerfumeDTO::of)
            .toList()
        );
    }

}
