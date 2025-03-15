package com.pikachu.purple.application.perfume.service;

import com.pikachu.purple.application.perfume.common.dto.RecommendedPerfumeDTO;
import com.pikachu.purple.application.perfume.common.dto.UserAccordDTO;
import com.pikachu.purple.application.perfume.common.vo.PerfumeAccordMatchVO;
import com.pikachu.purple.application.perfume.port.in.GetRecommendedPerfumesByUserAccordsUseCase;
import com.pikachu.purple.application.perfume.port.in.perfumeaccord.GetPerfumeAccordsUseCase;
import com.pikachu.purple.application.perfume.port.out.PerfumeRepository;
import com.pikachu.purple.application.user.port.in.useraccord.GetTopThreeUserAccordsUseCase;
import com.pikachu.purple.domain.accord.enums.Accord;
import com.pikachu.purple.domain.perfume.Perfume;
import com.pikachu.purple.domain.user.UserAccord;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
class GetRecommendedPerfumesByUserAccordsService implements
    GetRecommendedPerfumesByUserAccordsUseCase {

    private final GetTopThreeUserAccordsUseCase getTopThreeUserAccordsUseCase;
    private final GetPerfumeAccordsUseCase getPerfumeAccordsUseCase;

    private final PerfumeRepository perfumeRepository;

    private static final int MAX_SIZE = 30;

    @Override
    public Result findAllByUserId(Long userId) {
        GetTopThreeUserAccordsUseCase.Result result = getTopThreeUserAccordsUseCase.findByUserId(userId);

        if (result.userAccords().isEmpty()) {
            return new Result(
                Collections.emptyList(),
                Collections.emptyList()
            );
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
            .map(UserAccordDTO::accordKoreanName)
            .toList();

//        List<Accord> accords = new ArrayList<>(result.userAccords());
        List<Accord> accords = result.userAccords().stream()
            .map(UserAccord::getAccord)
            .toList();

        List<Perfume> perfumes = perfumeRepository.findAll(
            accords,
            MAX_SIZE
        );

        for (Perfume perfume : perfumes) {
            perfume.setAccords(
                getPerfumeAccordsUseCase
                    .findAll(perfume)
                    .perfumeAccords()
            );
        }

        List<RecommendedPerfumeDTO> recommendedPerfumeDTOs = perfumes.stream()
            .map(perfume -> {
                List<String> matchAccords = perfume.getAccords().stream()
                    .map(perfumeAccord -> perfumeAccord.getAccord().getKoreanName())
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
