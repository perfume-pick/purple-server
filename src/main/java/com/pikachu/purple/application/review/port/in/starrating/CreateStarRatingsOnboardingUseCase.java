package com.pikachu.purple.application.review.port.in.starrating;

import com.pikachu.purple.bootstrap.onboarding.vo.StarRatingVO;
import java.util.List;

public interface CreateStarRatingsOnboardingUseCase {

    void createAll(List<StarRatingVO> starRatingVOs);

}
