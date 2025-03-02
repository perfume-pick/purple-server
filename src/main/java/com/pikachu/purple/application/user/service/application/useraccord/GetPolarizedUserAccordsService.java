package com.pikachu.purple.application.user.service.application.useraccord;

import com.pikachu.purple.application.review.port.in.starrating.GetStarRatingsUseCase;
import com.pikachu.purple.application.user.common.dto.PolarizedUserAccordDTO;
import com.pikachu.purple.application.user.common.dto.PolarizedUserAccordDTO.AccordInfo;
import com.pikachu.purple.application.user.port.in.useraccord.GetPolarizedUserAccordsUseCase;
import com.pikachu.purple.application.user.port.out.UserAccordRepository;
import com.pikachu.purple.application.util.MathUtil;
import com.pikachu.purple.domain.perfume.Perfume;
import com.pikachu.purple.domain.review.StarRating;
import com.pikachu.purple.domain.user.UserAccord;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
class GetPolarizedUserAccordsService implements
    GetPolarizedUserAccordsUseCase {

    private final UserAccordRepository userAccordRepository;
    private final GetStarRatingsUseCase getStarRatingsUseCase;
    private static final int MAX_SIZE = 3;

    @Override
    public Result find(Long userId) {
        List<UserAccord> accordsByDesc = userAccordRepository.findAllOrderByScoreDesc(
            userId,
            MAX_SIZE
        );

        List<UserAccord> accordsByAsc = userAccordRepository.findAllOrderByScoreAsc(
            userId,
            MAX_SIZE
        );

        GetStarRatingsUseCase.Result result = getStarRatingsUseCase.findAllWithPerfume(userId);

        List<AccordInfo> preferredAccord = mapToAccordInfo(
            accordsByDesc,
            result.starRatings()
        );
        List<AccordInfo> dislikedAccord = mapToAccordInfo(
            accordsByAsc,
            result.starRatings()
        );

        return new Result(new PolarizedUserAccordDTO(
            preferredAccord,
            dislikedAccord)
        );
    }

    private List<AccordInfo> mapToAccordInfo(
        List<UserAccord> accords,
        List<StarRating> starRatings
    ) {
        Map<String, Integer> accordCountMap = new HashMap<>();

        for (StarRating starRating : starRatings) {
            Perfume perfume = starRating.getPerfume();
            if(perfume != null) {
                perfume.getAccords().stream()
                    .filter(perfumeAccord -> containsAccord(accords, perfumeAccord.getName()))
                    .forEach(perfumeAccord -> accordCountMap.merge(perfumeAccord.getName(), 1, Integer::sum));
            }
        }

        double totalScore = accords.stream()
            .mapToDouble(UserAccord::getScore).sum();

        return accords.stream()
            .map(userAccord -> {
                String accordName = userAccord.getName();
                int count = accordCountMap.getOrDefault(accordName, 0);
                int percentage = MathUtil.getPercentage(userAccord.getScore(), totalScore);
                return AccordInfo.of(
                    accordName,
                    userAccord.getKoreanName(),
                    count,
                    percentage
                );
            })
            .toList();
    }

    private boolean containsAccord(
        List<UserAccord> accords,
        String accordName
    ) {
        return accords.stream()
            .anyMatch(userAccord -> userAccord.getName().equals(accordName));
    }

}
