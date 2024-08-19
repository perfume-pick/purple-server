package com.pikachu.purple.application.review.service.application;

import com.pikachu.purple.application.review.port.in.ReviewGetUseCase;
import com.pikachu.purple.application.review.service.domain.ReviewDomainService;
import com.pikachu.purple.domain.review.Review;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewGetApplicationService implements ReviewGetUseCase {

    private final ReviewDomainService reviewDomainService;

    @Override
    public List<Review> findAllByUserId(Long userId) {
        return reviewDomainService.findAllByUserId(userId);
    }
}
