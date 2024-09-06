package com.pikachu.purple.application.review.service.application;

import static com.pikachu.purple.support.security.SecurityProvider.getCurrentUserAuthentication;

import com.pikachu.purple.application.rating.port.in.RatingCreateUseCase;
import com.pikachu.purple.application.rating.port.in.RatingCreateUseCase.Command;
import com.pikachu.purple.application.review.port.in.ReviewCreateDetailUseCase;
import com.pikachu.purple.application.review.service.domain.ReviewDomainService;
import com.pikachu.purple.application.userevaluation.port.in.UserEvaluationCreateUseCase;
import com.pikachu.purple.application.usermood.port.in.UserMoodCreateUseCase;
import com.pikachu.purple.application.util.IdGenerator;
import com.pikachu.purple.domain.rating.Rating;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewCreateDetailApplicationService implements ReviewCreateDetailUseCase {

    private final RatingCreateUseCase ratingCreateUseCase;
    private final ReviewDomainService reviewDomainService;
    private final UserEvaluationCreateUseCase userEvaluationCreateUseCase;
    private final UserMoodCreateUseCase userMoodCreateUseCase;

    @Override
    public void invoke(Command command) {
        Long userId = getCurrentUserAuthentication().userId();

        Rating rating = ratingCreateUseCase.create(
            new RatingCreateUseCase.Command(
                command.perfumeId(),
                command.score()
            )
        );

        reviewDomainService.create(
            IdGenerator.generate(),
            command.perfumeId(),
            userId,
            rating.getRatingId(),
            command.content()
        );

        userEvaluationCreateUseCase.invoke(
            new UserEvaluationCreateUseCase.Command(
                command.perfumeId(),
                command.evaluationForms()
            )
        );

        userMoodCreateUseCase.invoke(
            new UserMoodCreateUseCase.Command(
                command.perfumeId(),
                command.moods()
            )
        );
    }

}
