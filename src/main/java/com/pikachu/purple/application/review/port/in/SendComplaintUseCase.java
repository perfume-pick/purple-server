package com.pikachu.purple.application.review.port.in;

import com.pikachu.purple.domain.review.Complaint;

public interface SendComplaintUseCase {

    void invoke(Complaint complaint);

}
