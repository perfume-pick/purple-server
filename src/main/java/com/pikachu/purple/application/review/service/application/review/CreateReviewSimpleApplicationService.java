package com.pikachu.purple.application.review.service.application.review;

import static com.pikachu.purple.support.security.SecurityProvider.getCurrentUserAuthentication;

import com.pikachu.purple.application.review.port.in.review.CreateReviewSimpleUseCase;
import com.pikachu.purple.application.review.port.in.starrating.CreateStarRatingUseCase;
import com.pikachu.purple.application.review.port.in.starrating.GetStarRatingUseCase;
import com.pikachu.purple.application.review.port.in.starrating.UpdateStarRatingUseCase;
import com.pikachu.purple.application.review.service.domain.ReviewDomainService;
import com.pikachu.purple.domain.perfume.Perfume;
import com.pikachu.purple.domain.review.StarRating;
import com.pikachu.purple.domain.review.enums.ReviewType;
import com.pikachu.purple.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreateReviewSimpleApplicationService implements CreateReviewSimpleUseCase {

    private final ReviewDomainService reviewDomainService;
    private final GetStarRatingUseCase getStarRatingUseCase;
    private final CreateStarRatingUseCase createStarRatingUseCase;
    private final UpdateStarRatingUseCase updateStarRatingUseCase;

    @Transactional
    @Override
    public void invoke(Command command) {
        Long userId = getCurrentUserAuthentication().userId();

        GetStarRatingUseCase.Result getStarRatingResult = getStarRatingUseCase.invoke(
            new GetStarRatingUseCase.Command(
                userId,
                command.perfumeId()
            )
        );

        StarRating starRating;
        if (getStarRatingResult.starRating() == null) {
            CreateStarRatingUseCase.Result createStarRatingResult = createStarRatingUseCase.invoke(
                new CreateStarRatingUseCase.Command(
                    command.perfumeId(),
                    command.score()
                )
            );
            starRating = createStarRatingResult.starRating();
        } else {
            StarRating previousStarRating = getStarRatingResult.starRating();
            UpdateStarRatingUseCase.Result updateStarRatingResult = updateStarRatingUseCase.invoke(
                new UpdateStarRatingUseCase.Command(
                    previousStarRating.getPerfume().getId(),
                    previousStarRating.getScore(),
                    command.score()
                )
            );
            starRating = updateStarRatingResult.starRating();
        }


        User user = starRating.getUser();
        Perfume perfume = starRating.getPerfume();

        reviewDomainService.create(
            user.getId(),
            perfume.getId(),
            command.content(),
            ReviewType.SIMPLE
        );

    }

}
