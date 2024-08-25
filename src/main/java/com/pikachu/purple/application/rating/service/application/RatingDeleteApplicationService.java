package com.pikachu.purple.application.rating.service.application;

import static com.pikachu.purple.support.security.SecurityProvider.getCurrentUserAuthentication;

import com.pikachu.purple.application.rating.port.in.RatingDeleteUseCase;
import com.pikachu.purple.application.rating.service.domain.RatingDomainService;
import com.pikachu.purple.domain.rating.Rating;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RatingDeleteApplicationService implements RatingDeleteUseCase {

    private final RatingDomainService ratingDomainService;

    @Override
    public void invoke(Long ratingId) {
        Long userId = getCurrentUserAuthentication().userId();

        Rating rating = ratingDomainService.getByIdAndUserId(
            ratingId,
            userId
        );

        ratingDomainService.delete(rating);
    }

}
