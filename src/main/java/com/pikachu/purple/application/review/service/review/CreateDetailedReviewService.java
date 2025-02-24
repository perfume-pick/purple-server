package com.pikachu.purple.application.review.service.review;

import com.pikachu.purple.application.review.port.in.review.CreateDetailedReviewUseCase;
import com.pikachu.purple.application.review.port.in.review.CreateReviewUseCase;
import com.pikachu.purple.application.review.port.in.reviewevaluation.CreateReviewEvaluationUseCase;
import com.pikachu.purple.application.review.port.in.starrating.CreateOrUpdateStarRatingUseCase;
import com.pikachu.purple.application.review.port.out.ReviewRepository;
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

    private final CreateReviewUseCase createReviewUseCase;
    private final CreateOrUpdateStarRatingUseCase createOrUpdateStarRatingUseCase;
    private final CreateReviewEvaluationUseCase createReviewEvaluationUseCase;
    private final CreateUserAccordUseCase createUserAccordUseCase;

    private final ReviewRepository reviewRepository;

    @Transactional
    @Override
    public void create(
        Long userId,
        Long perfumeId,
        int score,
        String content,
        List<EvaluationFieldVO> evaluationFieldVOs,
        List<String> moodNames
    ) {
        CreateOrUpdateStarRatingUseCase.Result starRatingResult = createOrUpdateStarRatingUseCase.invoke(
            userId,
            perfumeId,
            score
        );

        StarRating starRating = starRatingResult.starRating();
        User user = starRating.getUser();
        Perfume perfume = starRating.getPerfume();

        Review review = createReviewUseCase.create(
            user.getId(),
            perfume.getId(),
            content,
            ReviewType.DETAIL
        ).review();

        reviewRepository.createReviewMoods(
            review.getId(),
            moodNames
        );

        createReviewEvaluationUseCase.create(
            review,
            evaluationFieldVOs
        );

        createUserAccordUseCase.createAll(
            userId,
            perfume.getId()
        );
    }

}
