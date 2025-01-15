package com.pikachu.purple.application.review.service.application.starrating;

import static com.pikachu.purple.support.security.SecurityProvider.getCurrentUserAuthentication;

import com.pikachu.purple.application.perfume.service.application.perfume.RecalculatePerfumeAverageScoreApplicationService;
import com.pikachu.purple.application.review.port.in.starrating.CreateStarRatingUseCase;
import com.pikachu.purple.application.review.service.domain.StarRatingDomainService;
import com.pikachu.purple.application.statistic.port.in.starratingstatistic.IncreaseStarRatingStatisticUseCase;
import com.pikachu.purple.domain.review.StarRating;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreateStarRatingApplicationService implements CreateStarRatingUseCase {

    private final StarRatingDomainService starRatingDomainService;
    private final IncreaseStarRatingStatisticUseCase increaseStarRatingStatisticUseCase;
    private final RecalculatePerfumeAverageScoreApplicationService recalculatePerfumeAverageScoreApplicationService;

    @Override
    @Transactional
    public Result invoke(
        Long perfumeId,
        int score
    ) {
        Long userId = getCurrentUserAuthentication().userId();

        StarRating starRating = starRatingDomainService.create(
            userId,
            perfumeId,
            score
        );

        increaseStarRatingStatisticUseCase.invoke(
            starRating.getPerfume().getId(),
            starRating.getScore()
        );

        // TODO: 별점 갱신 후 콜백으로 처리
//        recalculatePerfumeAverageScoreApplicationService.invoke(
//            new RecalculatePerfumeAverageScoreApplicationService.Command(command.perfumeId()));

        return new Result(starRating);
    }

}
