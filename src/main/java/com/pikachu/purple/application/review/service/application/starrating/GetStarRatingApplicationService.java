package com.pikachu.purple.application.review.service.application.starrating;

import com.pikachu.purple.application.review.port.in.starrating.GetStarRatingUseCase;
import com.pikachu.purple.application.review.service.domain.StarRatingDomainService;
import com.pikachu.purple.domain.review.StarRating;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class GetStarRatingApplicationService implements GetStarRatingUseCase {

    private final StarRatingDomainService starRatingDomainService;

    @Override
    public Result invoke(
        Long userId,
        Long perfumeId
    ) {
        StarRating starRating = starRatingDomainService.findByUserIdAndPerfumeId(
            userId,
            perfumeId
        );

        return new Result(starRating);
    }

}
