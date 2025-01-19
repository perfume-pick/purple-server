package com.pikachu.purple.application.review.service.application.starrating;

import com.pikachu.purple.application.review.port.in.starrating.GetStarRatingsUseCase;
import com.pikachu.purple.application.review.service.domain.StarRatingDomainService;
import com.pikachu.purple.domain.review.StarRating;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class GetStarRatingsApplicationService implements GetStarRatingsUseCase {

    private final StarRatingDomainService starRatingDomainService;

    @Override
    public Result invoke() {
        List<StarRating> starRatings = starRatingDomainService.findAll();
        return new Result(starRatings);
    }

}
