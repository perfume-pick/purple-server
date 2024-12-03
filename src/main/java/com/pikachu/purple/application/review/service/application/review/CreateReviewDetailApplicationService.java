package com.pikachu.purple.application.review.service.application.review;

import com.pikachu.purple.application.review.port.in.review.CreateReviewDetailUseCase;
import com.pikachu.purple.application.review.port.in.reviewevaluation.CreateReviewEvaluationUseCase;
import com.pikachu.purple.application.review.port.in.starrating.CreateOrUpdateStarRatingUseCase;
import com.pikachu.purple.application.review.service.domain.ReviewDomainService;
import com.pikachu.purple.application.user.port.in.useraccord.CreateUserAccordUseCase;
import com.pikachu.purple.domain.perfume.Perfume;
import com.pikachu.purple.domain.review.Review;
import com.pikachu.purple.domain.review.StarRating;
import com.pikachu.purple.domain.review.enums.ReviewType;
import com.pikachu.purple.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreateReviewDetailApplicationService implements CreateReviewDetailUseCase {

    private final ReviewDomainService reviewDomainService;
    private final CreateOrUpdateStarRatingUseCase createOrUpdateStarRatingUseCase;
    private final CreateReviewEvaluationUseCase createReviewEvaluationUseCase;
    private final CreateUserAccordUseCase createUserAccordUseCase;

    @Transactional
    @Override
    public void invoke(Command command) {
        CreateOrUpdateStarRatingUseCase.Result starRatingResult = createOrUpdateStarRatingUseCase.invoke(
            new CreateOrUpdateStarRatingUseCase.Command(
                command.perfumeId(),
                command.score()
            )
        );

        StarRating starRating = starRatingResult.starRating();
        User user = starRating.getUser();
        Perfume perfume = starRating.getPerfume();

        Review review = reviewDomainService.create(
            user.getId(),
            perfume.getId(),
            command.content(),
            ReviewType.DETAIL
        );

        reviewDomainService.createReviewMoods(
            review.getId(),
            command.moodNames()
        );

        createReviewEvaluationUseCase.invoke(
            new CreateReviewEvaluationUseCase.Command(
                review,
                command.evaluationFieldVOs()
            )
        );

        createUserAccordUseCase.invoke(new CreateUserAccordUseCase.Command(perfume.getId()));
    }

}
