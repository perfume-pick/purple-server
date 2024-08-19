package com.pikachu.purple.application.rating.port.in;

import com.pikachu.purple.bootstrap.review.vo.RatingValue;
import java.util.List;

public interface RatingCreateUseCase {

    void createOnboarding(OnboardingCommand command);
    void create(Command command);

    record OnboardingCommand(
        List<Long> reviewIds,
        List<RatingValue> ratingValues
    ) {

    }

    record Command(
        Long reviewId,
        Double score
    ) {

    }

}
