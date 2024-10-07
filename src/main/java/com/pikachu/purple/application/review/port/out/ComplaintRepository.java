package com.pikachu.purple.application.review.port.out;

import com.pikachu.purple.domain.review.Complaint;

public interface ComplaintRepository {

    Complaint create(Long complaintId, Long userId, Long reviewId, String token);

    Complaint find(Long complaintId, String token);
}
