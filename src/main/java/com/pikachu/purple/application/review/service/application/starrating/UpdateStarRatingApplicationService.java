package com.pikachu.purple.application.review.service.application.starrating;

import static com.pikachu.purple.support.security.SecurityProvider.getCurrentUserAuthentication;

import com.pikachu.purple.application.perfume.service.application.perfume.RecalculatePerfumeAverageScoreApplicationService;
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
public class UpdateStarRatingApplicationService implements UpdateStarRatingUseCase {

    private final StarRatingDomainService starRatingDomainService;
    private final DecreaseStarRatingStatisticUseCase decreaseStarRatingStatisticUseCase;
    private final IncreaseStarRatingStatisticUseCase increaseStarRatingStatisticUseCase;
    private final RecalculatePerfumeAverageScoreApplicationService recalculatePerfumeAverageScoreApplicationService;

    @Override
    @Transactional
    public Result invoke(Command command) {
        Long userId = getCurrentUserAuthentication().userId();

        decreaseStarRatingStatisticUseCase.invoke(
            new DecreaseStarRatingStatisticUseCase.Command(
                command.perfumeId(),
                command.previousScore()
            )
        );

        StarRating starRating = starRatingDomainService.updateScore(
            userId,
            command.perfumeId(),
            command.score()
        );

        increaseStarRatingStatisticUseCase.invoke(
            new IncreaseStarRatingStatisticUseCase.Command(
                starRating.getPerfume().getId(),
                starRating.getScore()
            )
        );

        // TODO: 별점 갱신 후 콜백으로 처리
//        recalculatePerfumeAverageScoreApplicationService.invoke(
//            new RecalculatePerfumeAverageScoreApplicationService.Command(command.perfumeId()));

        return new Result(starRating);
    }

}
