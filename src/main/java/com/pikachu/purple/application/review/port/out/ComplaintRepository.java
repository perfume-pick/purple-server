package com.pikachu.purple.application.review.port.out;

import com.pikachu.purple.domain.review.Complaint;
import java.util.List;

public interface ComplaintRepository {

    Complaint create(Long complaintId, Long userId, Long reviewId, String token);

    Complaint find(Long complaintId, String token);

    Complaint find(Long userId, Long reviewId);

    List<Complaint> findAll(Long userId, Long perfumeId);

    void delete(Long complaintId);

}
