package com.pikachu.purple.application.review.service.application;

import static com.pikachu.purple.support.security.SecurityProvider.getCurrentUserAuthentication;

import com.pikachu.purple.application.evaluation.port.in.EvaluationMoodCreateUseCase;
import com.pikachu.purple.application.rating.port.in.RatingCreateUseCase;
import com.pikachu.purple.application.review.port.in.ReviewCreateDetailUseCase;
import com.pikachu.purple.application.review.service.domain.ReviewDomainService;
import com.pikachu.purple.application.userevaluation.port.in.UserEvaluationCreateUseCase;
import com.pikachu.purple.domain.review.enums.ReviewType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewCreateDetailApplicationService implements ReviewCreateDetailUseCase {

    private final RatingCreateUseCase ratingCreateUseCase;
    private final ReviewDomainService reviewDomainService;
    private final UserEvaluationCreateUseCase userEvaluationCreateUseCase;
    private final EvaluationMoodCreateUseCase evaluationMoodCreateUseCase;

    @Override
    public void invoke(Command command) {
        Long userId = getCurrentUserAuthentication().userId();

        ratingCreateUseCase.invoke(
            new RatingCreateUseCase.Command(
                command.perfumeId(),
                command.score()
            )
        );

        reviewDomainService.create(
            command.perfumeId(),
            userId,
            command.content(),
            ReviewType.DETAIL
        );

        userEvaluationCreateUseCase.invoke(
            new UserEvaluationCreateUseCase.Command(
                command.perfumeId(),
                command.evaluationFieldVOs()
            )
        );

        evaluationMoodCreateUseCase.invoke(
            new EvaluationMoodCreateUseCase.Command(
                command.perfumeId(),
                command.moodNames()
            )
        );
    }

}
