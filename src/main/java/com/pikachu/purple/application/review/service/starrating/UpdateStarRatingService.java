package com.pikachu.purple.application.review.service.starrating;

import com.pikachu.purple.application.review.port.in.starrating.UpdateStarRatingUseCase;
import com.pikachu.purple.application.review.port.out.StarRatingRepository;
import com.pikachu.purple.application.statistic.port.in.starratingstatistic.DecreaseStarRatingStatisticUseCase;
import com.pikachu.purple.application.statistic.port.in.starratingstatistic.IncreaseStarRatingStatisticUseCase;
import com.pikachu.purple.domain.review.StarRating;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
class UpdateStarRatingService implements UpdateStarRatingUseCase {

    private final StarRatingRepository starRatingRepository;

    private final DecreaseStarRatingStatisticUseCase decreaseStarRatingStatisticUseCase;
    private final IncreaseStarRatingStatisticUseCase increaseStarRatingStatisticUseCase;

    @Transactional
    @Override
    public Result update(
        Long userId,
        Long perfumeId,
        int previousScore,
        int score
    ) {
        decreaseStarRatingStatisticUseCase.invoke(
            perfumeId,
            previousScore
        );

        StarRating starRating = starRatingRepository.updateScore(
            userId,
            perfumeId,
            score
        );

        increaseStarRatingStatisticUseCase.invoke(
            starRating.getPerfume().getId(),
            starRating.getScore()
        );

        // TODO: 별점 갱신 후 콜백으로 처리
//        recalculatePerfumeAverageScoreApplicationService.invoke(
//            new RecalculatePerfumeAverageScoreApplicationService.Command(command.perfumeId()));

        return new Result(starRating);
    }

}
