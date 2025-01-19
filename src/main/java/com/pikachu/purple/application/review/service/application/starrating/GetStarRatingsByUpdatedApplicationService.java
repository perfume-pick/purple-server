package com.pikachu.purple.application.review.service.application.starrating;

import com.pikachu.purple.application.review.port.in.starrating.GetStarRatingsByUpdatedDateUseCase;
import com.pikachu.purple.application.review.service.domain.StarRatingDomainService;
import com.pikachu.purple.domain.review.StarRating;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class GetStarRatingsByUpdatedApplicationService implements
    GetStarRatingsByUpdatedDateUseCase {

    private final StarRatingDomainService starRatingDomainService;

    @Override
    public Result invoke(Command command) {
        List<StarRating> starRatings = starRatingDomainService.findAllByUpdatedDate(command.updatedDate());
        return new Result(starRatings);
    }

}
