package com.pikachu.purple.application.review.service.application.starrating;

import static com.pikachu.purple.support.security.SecurityProvider.getCurrentUserAuthentication;

import com.pikachu.purple.application.review.port.in.starrating.UpdateStarRatingUseCase;
import com.pikachu.purple.application.review.service.domain.StarRatingDomainService;
import com.pikachu.purple.application.statistic.port.in.starratingstatistic.DecreaseStarRatingStatisticUseCase;
import com.pikachu.purple.application.statistic.port.in.starratingstatistic.IncreaseStarRatingStatisticUseCase;
import com.pikachu.purple.domain.review.StarRating;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateStarStarRatingApplicationService implements UpdateStarRatingUseCase {

    private final StarRatingDomainService starRatingDomainService;
    private final DecreaseStarRatingStatisticUseCase decreaseStarRatingStatisticUseCase;
    private final IncreaseStarRatingStatisticUseCase increaseStarRatingStatisticUseCase;

    @Override
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

        return new Result(starRating);
    }

}
