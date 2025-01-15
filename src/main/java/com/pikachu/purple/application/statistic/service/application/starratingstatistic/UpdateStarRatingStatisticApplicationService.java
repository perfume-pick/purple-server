package com.pikachu.purple.application.statistic.service.application.starratingstatistic;

import com.pikachu.purple.application.review.port.in.starrating.GetStarRatingUseCase;
import com.pikachu.purple.application.statistic.port.in.starratingstatistic.UpdateStarRatingStatisticUseCase;
import com.pikachu.purple.application.statistic.service.domain.StarRatingStatisticDomainService;
import com.pikachu.purple.domain.review.StarRating;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateStarRatingStatisticApplicationService implements
    UpdateStarRatingStatisticUseCase {

    private final StarRatingStatisticDomainService starRatingStatisticDomainService;
    private final GetStarRatingUseCase getStarRatingUseCase;

    @Override
    public void invoke(Command command) {
        GetStarRatingUseCase.Result result = getStarRatingUseCase.invoke(
            command.userId(),
            command.perfumeId()
        );

        StarRating previousStarRating = result.starRating();
        if (previousStarRating != null) {
            starRatingStatisticDomainService.decreaseVotes(
                command.perfumeId(),
                previousStarRating.getScore()
            );
        }

        starRatingStatisticDomainService.increaseVotes(
            command.perfumeId(),
            command.score()
        );

    }

}
