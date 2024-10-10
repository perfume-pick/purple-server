package com.pikachu.purple.application.review.service.domain;

import com.pikachu.purple.domain.review.Complaint;

public interface ComplaintDomainService {

    Complaint create(
        Long userId,
        Long reviewId
    );

    Complaint find(
        Long complaintId,
        String token
    );

    void delete(Long complaintId);

}
