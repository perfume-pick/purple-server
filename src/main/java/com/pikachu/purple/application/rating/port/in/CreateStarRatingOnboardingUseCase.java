package com.pikachu.purple.application.rating.port.in;

import com.pikachu.purple.bootstrap.onboarding.vo.StarRatingInfo;
import java.util.List;

public interface CreateStarRatingOnboardingUseCase {

    void invoke(Command command);

    record Command(List<StarRatingInfo> starRatingInfos) {

    }

}
