package com.pikachu.purple.application.rating.service.application;

import com.pikachu.purple.application.rating.port.in.RatingGetUseCase;
import com.pikachu.purple.application.rating.service.domain.RatingDomainService;
import com.pikachu.purple.domain.review.StarRating;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RatingGetApplicationService implements RatingGetUseCase {

    private final RatingDomainService ratingDomainService;

    @Override
    public List<StarRating> getAllByUserId(Long userId) {
        return ratingDomainService.getAllByUserId(userId);
    }

}
