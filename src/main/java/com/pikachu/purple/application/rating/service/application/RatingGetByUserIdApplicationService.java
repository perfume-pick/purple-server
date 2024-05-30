package com.pikachu.purple.application.rating.service.application;

import com.pikachu.purple.application.rating.port.in.RatingGetByUserIdUseCase;
import com.pikachu.purple.application.rating.service.domain.RatingDomainService;
import com.pikachu.purple.domain.rating.Rating;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RatingGetByUserIdApplicationService implements RatingGetByUserIdUseCase {

    private final RatingDomainService ratingDomainService;

    @Override
    public Result invoke(Long userId) {
        List<Rating> ratingList = ratingDomainService.getByUserId(userId);

        return new RatingGetByUserIdUseCase.Result(ratingList);
    }

}
