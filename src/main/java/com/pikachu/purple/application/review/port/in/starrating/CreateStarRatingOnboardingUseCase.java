package com.pikachu.purple.application.review.port.in.starrating;

import com.pikachu.purple.bootstrap.onboarding.vo.StarRatingVO;
import java.util.List;

public interface CreateStarRatingOnboardingUseCase {

    void invoke(Command command);

    record Command(List<StarRatingVO> starRatingVOs) {}

}
