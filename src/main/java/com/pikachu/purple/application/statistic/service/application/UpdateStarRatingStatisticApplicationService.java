package com.pikachu.purple.application.statistic.service.application;

import static com.pikachu.purple.bootstrap.common.exception.ErrorCode.*;

import com.pikachu.purple.application.rating.port.in.GetStarRatingUseCase;
import com.pikachu.purple.application.statistic.port.in.UpdateStarRatingStatisticUseCase;
import com.pikachu.purple.application.statistic.service.domain.StarRatingStatisticDomainService;
import com.pikachu.purple.bootstrap.common.exception.BusinessException;
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
            new GetStarRatingUseCase.Command(
                command.userId(),
                command.perfumeId()
            )
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
