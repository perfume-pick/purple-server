package com.pikachu.purple.application.rating.port.in;

import com.pikachu.purple.bootstrap.rating.vo.RatingValue;
import java.util.List;

public interface RatingCreateUseCase {

    void createOnboarding(OnboardingCommand command);
    Long create(Command command);

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
