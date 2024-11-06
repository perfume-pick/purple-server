package com.pikachu.purple.application.review.port.in.starrating;

import com.pikachu.purple.application.review.common.dto.ReviewWithPerfumeDTO;
import java.util.List;

public interface GetReviewsByUserAndSortTypeUseCase {

    Result invoke(Command command);

    record Command(String sortType) {}

    record Result(List<ReviewWithPerfumeDTO> reviewWithPerfumeDTOs) {}

}
