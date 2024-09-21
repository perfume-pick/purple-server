package com.pikachu.purple.application.perfume.service.application.perfume;

import static com.pikachu.purple.bootstrap.common.exception.BusinessException.PerfumeAccordNotFoundException;

import com.pikachu.purple.application.perfume.common.dto.PerfumeDTO;
import com.pikachu.purple.application.perfume.port.in.perfume.GetPerfumesByKeywordUseCase;
import com.pikachu.purple.application.perfume.service.domain.PerfumeDomainService;
import com.pikachu.purple.domain.accord.Accord;
import com.pikachu.purple.domain.perfume.Perfume;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetPerfumesByKeywordApplicationService implements GetPerfumesByKeywordUseCase {

    private final PerfumeDomainService perfumeDomainService;

    @Override
    @Transactional
    public Result invoke(Command command) {
        List<Perfume> perfumes = perfumeDomainService.findAllWithPerfumeAccordsByKeyword(command.keyword());

        List<PerfumeDTO> perfumeDTOs = perfumes.stream()
            .map(perfume -> PerfumeDTO.from(
                perfume,
                perfume.getAccords().stream()
                    .map(Accord::getName)
                    .findFirst()
                    .orElseThrow(() -> PerfumeAccordNotFoundException)
            ))
            .toList();

        return new Result(perfumeDTOs);
    }

}
