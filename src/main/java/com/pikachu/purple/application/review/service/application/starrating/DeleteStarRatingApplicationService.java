package com.pikachu.purple.application.review.service.application.starrating;

import com.pikachu.purple.application.review.port.in.starrating.DeleteStarRatingUseCase;
import com.pikachu.purple.application.review.service.domain.StarRatingDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeleteStarRatingApplicationService implements DeleteStarRatingUseCase {

    private final StarRatingDomainService starRatingDomainService;

    @Transactional
    @Override
    public void invoke(Long starRatingId) {
        starRatingDomainService.deleteById(starRatingId);
    }

}
