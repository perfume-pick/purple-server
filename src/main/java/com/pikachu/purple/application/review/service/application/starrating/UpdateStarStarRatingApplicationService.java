package com.pikachu.purple.application.review.service.application.starrating;

import static com.pikachu.purple.support.security.SecurityProvider.getCurrentUserAuthentication;

import com.pikachu.purple.application.review.port.in.starrating.GetStarRatingUseCase;
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
    private final GetStarRatingUseCase getStarRatingUseCase;
    private final DecreaseStarRatingStatisticUseCase decreaseStarRatingStatisticUseCase;
    private final IncreaseStarRatingStatisticUseCase increaseStarRatingStatisticUseCase;

    @Override
    public void invoke(Command command) {
        Long userId = getCurrentUserAuthentication().userId();

        GetStarRatingUseCase.Result result = getStarRatingUseCase.invoke(
            new GetStarRatingUseCase.Command(
                userId,
                command.perfumeId()
            )
        );

        StarRating previousStarRating = result.starRating();
        decreaseStarRatingStatisticUseCase.invoke(
            new DecreaseStarRatingStatisticUseCase.Command(
                previousStarRating.getPerfume().getId(),
                previousStarRating.getScore()
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
    }

}
