package com.pikachu.purple.application.review.service.starrating;

import com.pikachu.purple.application.perfume.port.in.perfume.RecalculatePerfumeAverageScoresUseCase;
import com.pikachu.purple.application.review.port.in.starrating.CreateStarRatingsOnboardingUseCase;
import com.pikachu.purple.application.review.port.out.StarRatingRepository;
import com.pikachu.purple.application.statistic.port.in.starratingstatistic.IncreaseStarRatingStatisticsUseCase;
import com.pikachu.purple.application.user.port.in.useraccord.CreateUserAccordOnboardingUseCase;
import com.pikachu.purple.application.util.IdUtil;
import com.pikachu.purple.bootstrap.onboarding.vo.StarRatingVO;
import com.pikachu.purple.domain.perfume.Perfume;
import com.pikachu.purple.domain.review.StarRating;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
class CreateStarRatingsOnboardingService implements
    CreateStarRatingsOnboardingUseCase {

    private final CreateUserAccordOnboardingUseCase createUserAccordOnboardingUseCase;
    private final StarRatingRepository starRatingRepository;
    private final IncreaseStarRatingStatisticsUseCase increaseStarRatingStatisticsUseCase;
    private final RecalculatePerfumeAverageScoresUseCase recalculatePerfumeAverageScoresUseCase;

    @Override
    @Transactional
    public void createAll(
        Long userId,
        List<StarRatingVO> starRatingVOs
    ) {
        List<StarRating> starRatings = new ArrayList<>();

        for(StarRatingVO starRatingVO : starRatingVOs) {
            Long starRatingId = IdUtil.generateId();

            Perfume perfume = Perfume.builder()
                .id(starRatingVO.perfumeId())
                .build();

            StarRating starRating = StarRating.builder()
                .id(starRatingId)
                .perfume(perfume)
                .score(starRatingVO.score())
                .build();

            starRatings.add(starRating);
        }

        starRatingRepository.createAll(
            userId,
            starRatings
        );

        increaseStarRatingStatisticsUseCase.invoke(starRatingVOs);

        // TODO: 별점 갱신 후 콜백으로 처리
//        List<Long> perfumeIds = command.starRatingVOs().stream()
//            .map(StarRatingVO::perfumeId).toList();
//        recalculatePerfumeAverageScoresUseCase.invoke(
//            new RecalculatePerfumeAverageScoresUseCase.Command(perfumeIds));

        createUserAccordOnboardingUseCase.createAll(userId);
    }

}
