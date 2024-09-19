package com.pikachu.purple.application.rating.service.application;

import com.pikachu.purple.application.rating.port.in.GetRatingsByUserIdUseCase;
import com.pikachu.purple.application.rating.service.domain.StarRatingDomainService;
import com.pikachu.purple.domain.review.StarRating;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetRatingsByUserIdApplicationService implements GetRatingsByUserIdUseCase {

    private final StarRatingDomainService starRatingDomainService;

    @Override
    public Result invoke(Command command) {
        List<StarRating> starRatings = starRatingDomainService.findAllWithPerfumeAndPerfumeAccordByUserId(command.userId());

        return new Result(starRatings);
    }

}
