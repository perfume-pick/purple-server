package com.pikachu.purple.application.review.port.in.complaint;

import com.pikachu.purple.application.review.common.dto.ComplaintFormDTO;

public interface GetComplaintFormUseCase {

    Result invoke(
        Long complaintId,
        String token
    );

    record Result(ComplaintFormDTO complaintFormDTO) {}

}
