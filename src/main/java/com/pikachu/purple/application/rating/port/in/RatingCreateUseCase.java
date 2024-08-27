package com.pikachu.purple.application.rating.port.in;

import com.pikachu.purple.bootstrap.rating.vo.RatingValue;
import com.pikachu.purple.domain.rating.Rating;
import java.util.List;

public interface RatingCreateUseCase {

    void createOnboarding(OnboardingCommand command);
    Rating create(Command command);

    record OnboardingCommand(
        List<RatingValue> ratingValues
    ) {

    }

    record Command(
        Long perfumeId,
        int score
    ) {

    }

}
