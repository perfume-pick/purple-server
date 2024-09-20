package com.pikachu.purple.application.rating.service.application;

import com.pikachu.purple.application.rating.port.in.DeleteStarRatingUseCase;
import com.pikachu.purple.application.rating.service.domain.StarRatingDomainService;
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
