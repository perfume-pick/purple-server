package com.pikachu.purple.application.review.service.application.starrating;

import com.pikachu.purple.application.review.port.in.starrating.GetStarRatingsByUserIdUseCase;
import com.pikachu.purple.application.review.service.domain.StarRatingDomainService;
import com.pikachu.purple.domain.review.StarRating;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetStarRatingsByUserIdApplicationService implements GetStarRatingsByUserIdUseCase {

    private final StarRatingDomainService starRatingDomainService;

    @Override
    public Result invoke(Command command) {
        List<StarRating> starRatings = starRatingDomainService.findAllWithPerfumeAndPerfumeAccordByUserId(command.userId());

        return new Result(starRatings);
    }

}
