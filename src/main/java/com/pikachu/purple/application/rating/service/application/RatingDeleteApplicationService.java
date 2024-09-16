package com.pikachu.purple.application.rating.service.application;

import static com.pikachu.purple.support.security.SecurityProvider.getCurrentUserAuthentication;

import com.pikachu.purple.application.rating.port.in.RatingDeleteUseCase;
import com.pikachu.purple.application.rating.service.domain.RatingDomainService;
import com.pikachu.purple.domain.review.StarRating;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RatingDeleteApplicationService implements RatingDeleteUseCase {

    private final RatingDomainService ratingDomainService;

    @Override
    public void invoke(Long perfumeId) {
        Long userId = getCurrentUserAuthentication().userId();

        StarRating starRating = ratingDomainService.findByPerfumeIdAndUserId(
            perfumeId,
            userId
        );

        ratingDomainService.delete(starRating);
    }

}
