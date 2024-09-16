package com.pikachu.purple.application.perfume.service.application;

import com.pikachu.purple.application.perfume.common.dto.PerfumeDTO;
import com.pikachu.purple.application.perfume.port.in.GetPerfumesByKeywordUseCase;
import com.pikachu.purple.application.perfume.service.domain.PerfumeDomainService;
import com.pikachu.purple.domain.accord.Accord;
import com.pikachu.purple.domain.perfume.Perfume;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetPerfumesByKeywordApplicationService implements GetPerfumesByKeywordUseCase {

    private final PerfumeDomainService perfumeDomainService;

    @Override
    public Result invoke(Command command) {
        List<Perfume> perfumes = perfumeDomainService.findAllByKeyword(command.keyword());

        //TODO null -> Exception 처리
        List<PerfumeDTO> perfumeDTOs = perfumes.stream()
            .map(perfume -> PerfumeDTO.from(
                perfume,
                perfume.getAccords().stream()
                    .map(Accord::getName)
                    .findFirst()
                    .orElse(null)
            ))
            .toList();

        return new Result(perfumeDTOs);
    }

}
