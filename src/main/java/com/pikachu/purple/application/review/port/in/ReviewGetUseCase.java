package com.pikachu.purple.application.review.port.in;

import com.pikachu.purple.domain.review.Review;
import java.util.List;

public interface ReviewGetUseCase {

    List<Review> findAllByUserId(Long userId);

}
