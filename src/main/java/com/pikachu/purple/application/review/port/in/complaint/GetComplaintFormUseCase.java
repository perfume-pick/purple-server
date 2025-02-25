package com.pikachu.purple.application.review.port.in.complaint;

import java.time.Instant;

public interface GetComplaintFormUseCase {

    Result invoke(
        Long complaintId,
        String token
    );

    record Result(ComplaintFormDTO complaintFormDTO) {}

    record ComplaintFormDTO(
        Instant reportedAt,
        Long reporterId,
        Long reportedId,
        Long perfumeId,
        String perfumeName,
        String comment,
        String link
    ) {}

}
