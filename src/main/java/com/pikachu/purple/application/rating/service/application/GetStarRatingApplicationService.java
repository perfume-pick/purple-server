package com.pikachu.purple.application.rating.service.application;

import com.pikachu.purple.application.rating.port.in.GetStarRatingUseCase;
import com.pikachu.purple.application.rating.service.domain.StarRatingDomainService;
import com.pikachu.purple.domain.review.StarRating;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetStarRatingApplicationService implements GetStarRatingUseCase {

    private final StarRatingDomainService starRatingDomainService;

    @Override
    public Result invoke(Command command) {
        StarRating starRating = starRatingDomainService.findByUserIdAndPerfumeId(
            command.userId(),
            command.perfumeId()
        );

        return new Result(starRating);
    }
}
