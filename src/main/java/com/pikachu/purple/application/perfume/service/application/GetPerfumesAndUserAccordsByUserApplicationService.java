package com.pikachu.purple.application.perfume.service.application;

import com.pikachu.purple.application.perfume.common.dto.RecommendedPerfumeDTO;
import com.pikachu.purple.application.perfume.port.in.GetPerfumesAndUserAccordsByUserUseCase;
import com.pikachu.purple.application.perfume.service.domain.PerfumeAccordDomainService;
import com.pikachu.purple.application.perfume.service.domain.PerfumeDomainService;
import com.pikachu.purple.application.useraccrod.port.in.GetUserAccordsUseCase;
import com.pikachu.purple.domain.accord.Accord;
import com.pikachu.purple.domain.perfume.Perfume;
import com.pikachu.purple.domain.perfume.PerfumeAccord;
import com.pikachu.purple.domain.user.UserAccord;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetPerfumesAndUserAccordsByUserApplicationService implements
    GetPerfumesAndUserAccordsByUserUseCase {

    private final PerfumeDomainService perfumeDomainService;
    private final GetUserAccordsUseCase getUserAccordsUseCase;

    @Override
    public Result invoke() {
        GetUserAccordsUseCase.Result result = getUserAccordsUseCase.invoke();

        List<Accord> accords = new ArrayList<>(result.userAccords());
        List<Perfume> perfumes = perfumeDomainService.findAllWithPerfumeAccordsByAccords(
            accords);

        List<RecommendedPerfumeDTO> recommendedPerfumeDTOs = perfumes.stream()
            .map(perfume -> {
                List<String> accordNames = perfume.getAccords().stream()
                    .map(Accord::getName)
                    .filter(name -> result.userAccords().stream()
                        .anyMatch(userAccord ->
                            userAccord.getName().equals(name)))
                    .toList();
                return RecommendedPerfumeDTO.from(
                    perfume,
                    accordNames
                );
            })
            .toList();

        return new Result(
            result.userAccords(),
            recommendedPerfumeDTOs
        );
    }

}
