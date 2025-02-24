package com.pikachu.purple.application.review.service.starrating;

import com.pikachu.purple.application.review.port.in.starrating.GetStarRatingUseCase;
import com.pikachu.purple.application.review.port.out.StarRatingRepository;
import com.pikachu.purple.domain.review.StarRating;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class GetStarRatingService implements GetStarRatingUseCase {

    private final StarRatingRepository starRatingRepository;

    @Override
    public Result find(
        Long userId,
        Long perfumeId
    ) {
        StarRating starRating = starRatingRepository.findByUserIdAndPerfumeId(
            userId,
            perfumeId
        );

        return new Result(starRating);
    }

}
