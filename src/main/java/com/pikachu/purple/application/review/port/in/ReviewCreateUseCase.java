package com.pikachu.purple.application.review.port.in;

import com.pikachu.purple.bootstrap.review.vo.RatingValue;
import java.util.List;

public interface ReviewCreateUseCase {

    void createOnboarding(OnboardingCommand command);

    void create(Command command);

    record OnboardingCommand(List<RatingValue> ratingValues) {

    }

    record Command(
        Long perfumeId,
        Double score,
        String content
    ) {

    }

}
