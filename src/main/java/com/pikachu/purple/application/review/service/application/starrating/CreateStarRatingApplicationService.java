package com.pikachu.purple.application.review.service.application.starrating;

import static com.pikachu.purple.support.security.SecurityProvider.getCurrentUserAuthentication;

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

    @Transactional
    @Override

    public Result invoke(Command command) {
        Long userId = getCurrentUserAuthentication().userId();

        StarRating starRating = starRatingDomainService.create(
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
