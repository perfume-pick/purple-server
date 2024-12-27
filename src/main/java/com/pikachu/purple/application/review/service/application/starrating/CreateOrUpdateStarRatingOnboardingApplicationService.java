package com.pikachu.purple.application.review.service.application.starrating;

import static com.pikachu.purple.support.security.SecurityProvider.getCurrentUserAuthentication;

import com.pikachu.purple.application.perfume.service.application.perfume.RecalculatePerfumeAverageScoreApplicationService;
import com.pikachu.purple.application.review.port.in.starrating.CreateOrUpdateStarRatingUseCase;
import com.pikachu.purple.application.review.port.in.starrating.CreateStarRatingUseCase;
import com.pikachu.purple.application.review.port.in.starrating.DeleteStarRatingUseCase;
import com.pikachu.purple.application.review.port.in.starrating.GetStarRatingUseCase;
import com.pikachu.purple.application.review.port.in.starrating.UpdateStarRatingUseCase;
import com.pikachu.purple.domain.review.StarRating;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateOrUpdateStarRatingOnboardingApplicationService implements
    CreateOrUpdateStarRatingUseCase {

    private final GetStarRatingUseCase getStarRatingUseCase;
    private final CreateStarRatingUseCase createStarRatingUseCase;
    private final UpdateStarRatingUseCase updateStarRatingUseCase;


    @Override
    public Result invoke(Command command) {
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

        return new Result(starRating);
    }

}
