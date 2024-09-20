package com.pikachu.purple.application.review.port.in;

import com.pikachu.purple.application.review.common.dto.ReviewDTO;
import java.util.List;

public interface GetReviewsByPerfumeIdAndSortTypeUseCase {

    Result invoke(Command command);

    record Command(
        Long perfumeId,
        String sortType
    ) {}

    record Result(List<ReviewDTO> reviewDTOs) {}

}
