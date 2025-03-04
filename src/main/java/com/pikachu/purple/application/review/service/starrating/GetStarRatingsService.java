package com.pikachu.purple.application.review.service.starrating;

import com.pikachu.purple.application.perfume.port.in.perfume.GetPerfumeUseCase;
import com.pikachu.purple.application.perfume.port.in.perfumeaccord.GetPerfumeAccordsUseCase;
import com.pikachu.purple.application.review.port.in.starrating.GetStarRatingsUseCase;
import com.pikachu.purple.application.review.port.out.StarRatingRepository;
import com.pikachu.purple.domain.perfume.Perfume;
import com.pikachu.purple.domain.review.StarRating;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
class GetStarRatingsService implements GetStarRatingsUseCase {

    private final GetPerfumeAccordsUseCase getPerfumeAccordsUseCase;
    private final GetPerfumeUseCase getPerfumeUseCase;

    private final StarRatingRepository starRatingRepository;

    @Transactional
    @Override
    public Result findAll() {
        List<StarRating> starRatings = starRatingRepository.findAll();
        return new Result(starRatings);
    }

    @Transactional
    @Override
    public Result findAllByUserIdWithPerfume(Long userId) {
        List<StarRating> starRatings = starRatingRepository.findAllByUserId(userId);
        for (StarRating starRating : starRatings) {
            Perfume perfume = getPerfumeUseCase
                .findByPerfumeId(starRating.getPerfume().getId())
                .perfume();

            perfume.setAccords(
                getPerfumeAccordsUseCase
                    .findAll(starRating.getPerfume())
                    .perfumeAccords()
            );

            starRating.setPerfume(perfume);

        }

        return new Result(starRatings);
    }

}
