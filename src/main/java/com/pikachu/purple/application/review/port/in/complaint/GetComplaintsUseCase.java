package com.pikachu.purple.application.review.port.in.complaint;

import com.pikachu.purple.domain.review.Complaint;
import java.util.List;

public interface GetComplaintsUseCase {

    Result findAllByUserIdAndPerfumeId(
        Long userId,
        Long perfumeId
    );

    record Result(List<Complaint> complaints) {}

}
