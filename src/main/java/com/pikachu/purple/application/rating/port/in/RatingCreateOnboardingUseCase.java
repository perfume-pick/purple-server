package com.pikachu.purple.application.rating.port.in;

import com.pikachu.purple.bootstrap.rating.vo.RatingInfo;
import java.util.List;

public interface RatingCreateOnboardingUseCase {

    void invoke(Command command);

    record Command(List<RatingInfo> ratingInfos) {

    }

}
