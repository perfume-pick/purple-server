package com.pikachu.purple.application.review.service.application.starrating;

import com.pikachu.purple.application.review.port.in.starrating.UpdateStarRatingUseCase;
import com.pikachu.purple.application.review.service.domain.StarRatingDomainService;
import com.pikachu.purple.application.statistic.port.in.starratingstatistic.DecreaseStarRatingStatisticUseCase;
import com.pikachu.purple.application.statistic.port.in.starratingstatistic.IncreaseStarRatingStatisticUseCase;
import com.pikachu.purple.domain.review.StarRating;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
class UpdateStarRatingService implements UpdateStarRatingUseCase {

    private final StarRatingDomainService starRatingDomainService;
    private final DecreaseStarRatingStatisticUseCase decreaseStarRatingStatisticUseCase;
    private final IncreaseStarRatingStatisticUseCase increaseStarRatingStatisticUseCase;

    @Override
    @Transactional
    public Result update(
        Long userId,
        Long perfumeId,
        int previousScore,
        int score
    ) {
        decreaseStarRatingStatisticUseCase.invoke(
            perfumeId,
            previousScore
        );

        StarRating starRating = starRatingDomainService.updateScore(
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
