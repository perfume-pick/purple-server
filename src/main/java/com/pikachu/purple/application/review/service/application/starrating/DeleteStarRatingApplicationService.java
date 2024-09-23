package com.pikachu.purple.application.review.service.application.starrating;

import com.pikachu.purple.application.review.port.in.starrating.DeleteStarRatingUseCase;
import com.pikachu.purple.application.review.service.domain.StarRatingDomainService;
import com.pikachu.purple.application.statistic.port.in.starratingstatistic.DecreaseStarRatingStatisticUseCase;
import com.pikachu.purple.domain.review.StarRating;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeleteStarRatingApplicationService implements DeleteStarRatingUseCase {

    private final StarRatingDomainService starRatingDomainService;
    private final DecreaseStarRatingStatisticUseCase decreaseStarRatingStatisticUseCase;

    @Transactional
    @Override
    public void invoke(Command command) {
        StarRating starRating = starRatingDomainService.deleteById(command.starRatingId());

        decreaseStarRatingStatisticUseCase.invoke(
            new DecreaseStarRatingStatisticUseCase.Command(
                starRating.getPerfume().getId(),
                starRating.getScore()
            )
        );
    }

}
