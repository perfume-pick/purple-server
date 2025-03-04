package com.pikachu.purple.application.review.service.starrating;

import com.pikachu.purple.application.review.port.in.starrating.CreateOrUpdateStarRatingUseCase;
import com.pikachu.purple.application.review.port.in.starrating.CreateStarRatingUseCase;
import com.pikachu.purple.application.review.port.in.starrating.GetStarRatingUseCase;
import com.pikachu.purple.application.review.port.in.starrating.UpdateStarRatingUseCase;
import com.pikachu.purple.domain.review.StarRating;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
class CreateOrUpdateStarRatingService implements
    CreateOrUpdateStarRatingUseCase {

    private final GetStarRatingUseCase getStarRatingUseCase;
    private final CreateStarRatingUseCase createStarRatingUseCase;
    private final UpdateStarRatingUseCase updateStarRatingUseCase;


    @Transactional
    @Override
    public Result invoke(
        Long userId,
        Long perfumeId,
        int score
    ) {
        GetStarRatingUseCase.Result getStarRatingResult = getStarRatingUseCase.findByUserIdAndPerfumeId(
            userId,
            perfumeId
        );

        StarRating starRating;
        if (getStarRatingResult.starRating() == null) {
            CreateStarRatingUseCase.Result createStarRatingResult = createStarRatingUseCase.create(
                userId,
                perfumeId,
                score
            );
            starRating = createStarRatingResult.starRating();
        } else {
            StarRating previousStarRating = getStarRatingResult.starRating();
            UpdateStarRatingUseCase.Result updateStarRatingResult = updateStarRatingUseCase.update(
                userId,
                previousStarRating.getPerfume().getId(),
                previousStarRating.getScore(),
                score
            );
            starRating = updateStarRatingResult.starRating();
        }

        return new Result(starRating);
    }

}
