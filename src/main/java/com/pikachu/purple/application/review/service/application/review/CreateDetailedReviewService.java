package com.pikachu.purple.application.review.service.application.review;

import com.pikachu.purple.application.review.port.in.review.CreateDetailedReviewUseCase;
import com.pikachu.purple.application.review.port.in.reviewevaluation.CreateReviewEvaluationUseCase;
import com.pikachu.purple.application.review.port.in.starrating.CreateOrUpdateStarRatingUseCase;
import com.pikachu.purple.application.review.service.domain.ReviewDomainService;
import com.pikachu.purple.application.user.port.in.useraccord.CreateUserAccordUseCase;
import com.pikachu.purple.bootstrap.review.vo.EvaluationFieldVO;
import com.pikachu.purple.domain.perfume.Perfume;
import com.pikachu.purple.domain.review.Review;
import com.pikachu.purple.domain.review.StarRating;
import com.pikachu.purple.domain.review.enums.ReviewType;
import com.pikachu.purple.domain.user.User;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
class CreateDetailedReviewService implements CreateDetailedReviewUseCase {

    private final ReviewDomainService reviewDomainService;
    private final CreateOrUpdateStarRatingUseCase createOrUpdateStarRatingUseCase;
    private final CreateReviewEvaluationUseCase createReviewEvaluationUseCase;
    private final CreateUserAccordUseCase createUserAccordUseCase;

    @Transactional
    @Override
    public void invoke(
        Long perfumeId,
        int score,
        String content,
        List<EvaluationFieldVO> evaluationFieldVOs,
        List<String> moodNames
    ) {
        CreateOrUpdateStarRatingUseCase.Result starRatingResult = createOrUpdateStarRatingUseCase.invoke(
            perfumeId,
            score
        );

        StarRating starRating = starRatingResult.starRating();
        User user = starRating.getUser();
        Perfume perfume = starRating.getPerfume();

        Review review = reviewDomainService.create(
            user.getId(),
            perfume.getId(),
            content,
            ReviewType.DETAIL
        );

        reviewDomainService.createReviewMoods(
            review.getId(),
            moodNames
        );

        createReviewEvaluationUseCase.invoke(
            review,
            evaluationFieldVOs
        );

        createUserAccordUseCase.invoke(perfume.getId());
    }

}
