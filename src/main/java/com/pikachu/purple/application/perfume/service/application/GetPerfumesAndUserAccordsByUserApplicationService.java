package com.pikachu.purple.application.perfume.service.application;

import static com.pikachu.purple.bootstrap.common.exception.BusinessException.UserAccordNotFoundException;

import com.pikachu.purple.application.perfume.common.dto.RecommendedPerfumeDTO;
import com.pikachu.purple.application.perfume.common.dto.UserAccordDTO;
import com.pikachu.purple.application.perfume.common.vo.PerfumeAccordMatchVO;
import com.pikachu.purple.application.perfume.port.in.GetPerfumesAndUserAccordsByUserUseCase;
import com.pikachu.purple.application.perfume.service.domain.PerfumeDomainService;
import com.pikachu.purple.application.review.port.in.starrating.GetAverageScoreByPerfumeIdUseCase;
import com.pikachu.purple.application.user.port.in.useraccord.GetTopThreeUserAccordsUseCase;
import com.pikachu.purple.domain.accord.Accord;
import com.pikachu.purple.domain.perfume.Perfume;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GetPerfumesAndUserAccordsByUserApplicationService implements
    GetPerfumesAndUserAccordsByUserUseCase {

    private final PerfumeDomainService perfumeDomainService;
    private final GetTopThreeUserAccordsUseCase getTopThreeUserAccordsUseCase;
    private final GetAverageScoreByPerfumeIdUseCase getAverageScoreByPerfumeIdUseCase;

    private static final int MAX_SIZE = 30;

    @Transactional
    @Override
    public Result invoke() {
        GetTopThreeUserAccordsUseCase.Result result = getTopThreeUserAccordsUseCase.invoke();

        if (result.userAccords().isEmpty()) {
            throw UserAccordNotFoundException;
        }

        List<UserAccordDTO> userAccordDTOs = IntStream.range(0, 3)
            .mapToObj(i -> UserAccordDTO.of(
                result.userAccords().stream()
                    .sorted((a, b) -> Double.compare(b.getScore(), a.getScore()))
                    .toList()
                    .get(i),
                i + 1
            ))
            .toList();

        List<String> topThreeUserAccordNames = userAccordDTOs.stream()
            .map(UserAccordDTO::accordName)
            .toList();

        List<Accord> accords = new ArrayList<>(result.userAccords());
        List<Perfume> perfumes = perfumeDomainService.findAllWithPerfumeAccordsByAccords(
            accords,
            MAX_SIZE
        );

        for (Perfume perfume : perfumes) {
            double averageScore = getAverageScoreByPerfumeIdUseCase.invoke(
                new GetAverageScoreByPerfumeIdUseCase.Command(perfume.getId())).averageScore();
            perfume.setAverageScore(averageScore);
        }

        List<RecommendedPerfumeDTO> recommendedPerfumeDTOs = perfumes.stream()
            .map(perfume -> {
                List<String> matchAccords = perfume.getAccords().stream()
                    .map(Accord::getName)
                    .filter(topThreeUserAccordNames::contains)
                    .toList();

                return new PerfumeAccordMatchVO(
                    perfume,
                    matchAccords.size(),
                    matchAccords
                );
            })
            .sorted((p1, p2) -> Integer.compare(p2.count(), p1.count()))
            .map(perfumeAccordMatchVO -> RecommendedPerfumeDTO.from(
                perfumeAccordMatchVO.perfume(),
                perfumeAccordMatchVO.matchAccords()
            ))
            .toList();

        return new Result(
            userAccordDTOs,
            recommendedPerfumeDTOs
        );
    }

}
