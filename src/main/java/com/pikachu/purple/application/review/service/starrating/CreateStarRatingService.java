package com.pikachu.purple.application.review.service.starrating;

import com.pikachu.purple.application.review.port.in.starrating.CreateStarRatingUseCase;
import com.pikachu.purple.application.review.port.out.StarRatingRepository;
import com.pikachu.purple.application.statistic.port.in.starratingstatistic.IncreaseStarRatingStatisticUseCase;
import com.pikachu.purple.application.util.IdUtil;
import com.pikachu.purple.domain.review.StarRating;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
class CreateStarRatingService implements CreateStarRatingUseCase {

    private final StarRatingRepository starRatingRepository;
    private final IncreaseStarRatingStatisticUseCase increaseStarRatingStatisticUseCase;

    @Transactional
    @Override
    public Result create(
        Long userId,
        Long perfumeId,
        int score
    ) {
        Long starRatingId = IdUtil.generateId();

        StarRating starRating = starRatingRepository.create(
            starRatingId,
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
