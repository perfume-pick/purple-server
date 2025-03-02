package com.pikachu.purple.application.review.service.starrating;

import com.pikachu.purple.application.perfume.port.in.perfume.GetPerfumeUseCase;
import com.pikachu.purple.application.perfume.port.in.perfumeaccord.GetPerfumeAccordsUseCase;
import com.pikachu.purple.application.review.port.in.starrating.GetStarRatingUseCase;
import com.pikachu.purple.application.review.port.out.StarRatingRepository;
import com.pikachu.purple.domain.perfume.Perfume;
import com.pikachu.purple.domain.review.StarRating;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class GetStarRatingService implements GetStarRatingUseCase {

    private final GetPerfumeUseCase getPerfumeUseCase;
    private final GetPerfumeAccordsUseCase getPerfumeAccordsUseCase;

    private final StarRatingRepository starRatingRepository;

    @Override
    public Result find(
        Long userId,
        Long perfumeId
    ) {
        StarRating starRating = starRatingRepository.find(
            userId,
            perfumeId
        );

        return new Result(starRating);
    }

    @Override
    public Result findWithPerfumeAndPerfumeAccords(
        Long userId,
        Long perfumeId
    ) {
        StarRating starRating = starRatingRepository.find(
            userId,
            perfumeId
        );

        Perfume perfume = getPerfumeUseCase.find(perfumeId).perfume();
        perfume.setAccords(
            getPerfumeAccordsUseCase
                .findAll(starRating.getPerfume())
                .perfumeAccords()
        );

        starRating.setPerfume(perfume);

        return new Result(starRating);
    }

}
