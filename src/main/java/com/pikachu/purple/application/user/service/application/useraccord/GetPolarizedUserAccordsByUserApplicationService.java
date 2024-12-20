package com.pikachu.purple.application.user.service.application.useraccord;

import static com.pikachu.purple.support.security.SecurityProvider.getCurrentUserAuthentication;

import com.pikachu.purple.application.review.port.in.starrating.GetStarRatingsByUserIdUseCase;
import com.pikachu.purple.application.user.common.dto.PolarizedUserAccordDTO;
import com.pikachu.purple.application.user.common.dto.PolarizedUserAccordDTO.AccordInfo;
import com.pikachu.purple.application.user.port.in.useraccord.GetPolarizedUserAccordsByUserUseCase;
import com.pikachu.purple.application.user.service.domain.UserAccordDomainService;
import com.pikachu.purple.application.util.MathUtil;
import com.pikachu.purple.domain.accord.Accord;
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
public class GetPolarizedUserAccordsByUserApplicationService implements
    GetPolarizedUserAccordsByUserUseCase {

    private final UserAccordDomainService userAccordDomainService;
    private final GetStarRatingsByUserIdUseCase getStarRatingsByUserIdUseCase;
    private static final int MAX_SIZE = 3;

    @Transactional
    @Override
    public Result invoke() {
        Long userId = getCurrentUserAuthentication().userId();

        List<UserAccord> accordsByDesc = userAccordDomainService.findAllOrderByScoreDesc(
            userId,
            MAX_SIZE
        );

        List<UserAccord> accordsByAsc = userAccordDomainService.findAllOrderByScoreAsc(
            userId,
            MAX_SIZE
        );

        GetStarRatingsByUserIdUseCase.Result result = getStarRatingsByUserIdUseCase.invoke(
            new GetStarRatingsByUserIdUseCase.Command(userId)
        );

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
        Map<String, Integer> percentMap = new HashMap<>();

        for (StarRating starRating : starRatings) {
            Perfume perfume = starRating.getPerfume();
            if (perfume != null) {
                for (Accord perfumeAccord : perfume.getAccords()) {
                    String accordName = perfumeAccord.getKoreanName();

                    if (containsAccord(
                        accords,
                        accordName
                    )) {
                        accordCountMap.put(
                            accordName,
                            accordCountMap.getOrDefault(accordName, 0) + 1
                        );
                    }
                }
            }
        }

        double totalScore = accords.stream()
            .mapToDouble(UserAccord::getScore).sum();

        for(UserAccord userAccord : accords) {
            String accordName = userAccord.getKoreanName();

            percentMap.put(
                accordName,
                MathUtil.getPercentage(
                    userAccord.getScore(),
                    totalScore
                )
            );
        }

        return accordCountMap.keySet().stream()
            .filter(percentMap::containsKey)
            .map(accordName -> AccordInfo.of(
                accordName,
                accordCountMap.get(accordName),
                percentMap.get(accordName)
            ))
            .toList();
    }

    private boolean containsAccord(
        List<UserAccord> accords,
        String accordName
    ) {
        return accords.stream()
            .anyMatch(userAccord -> userAccord.getKoreanName().equals(accordName));
    }

}
