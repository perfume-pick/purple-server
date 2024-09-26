package com.pikachu.purple.application.review.port.in.review;

import com.pikachu.purple.application.review.common.dto.ReviewDTO;
import com.pikachu.purple.application.util.IdUtil;
import java.util.List;

public interface GetReviewsByPerfumeIdAndSortTypeUseCase {

    Result invoke(Command command);

    record Command(
        Long perfumeId,
        String sortType
    ) {

        public Command(
            String perfumeId,
            String sortType
        ) {
            this(
                IdUtil.from(perfumeId),
                sortType
            );
        }

    }

    record Result(List<ReviewDTO> reviewDTOs) {}

}
