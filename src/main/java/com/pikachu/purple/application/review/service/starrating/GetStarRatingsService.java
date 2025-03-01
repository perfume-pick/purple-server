package com.pikachu.purple.application.review.service.starrating;

import com.pikachu.purple.application.perfume.port.in.perfumeaccord.GetPerfumeAccordsUseCase;
import com.pikachu.purple.application.review.port.in.starrating.GetStarRatingsUseCase;
import com.pikachu.purple.application.review.port.out.StarRatingRepository;
import com.pikachu.purple.domain.review.StarRating;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class GetStarRatingsService implements GetStarRatingsUseCase {

    private final GetPerfumeAccordsUseCase getPerfumeAccordsUseCase;

    private final StarRatingRepository starRatingRepository;

    @Override
    public Result findAll() {
        List<StarRating> starRatings = starRatingRepository.findAll();
        return new Result(starRatings);
    }

    @Override
    public Result findAll(Long userId) {
        List<StarRating> starRatings = starRatingRepository.findAllWithPerfume(userId);
        for (StarRating starRating : starRatings) {
            starRating
                .getPerfume()
                .setAccords(
                    getPerfumeAccordsUseCase
                        .findAll(starRating.getPerfume())
                        .perfumeAccords()
                );
        }

        return new Result(starRatings);
    }

}
