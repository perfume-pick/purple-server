package com.pikachu.purple.application.review.port.in.complaint;

import com.pikachu.purple.domain.review.Complaint;

public interface SendComplaintUseCase {

    void send(Complaint complaint);

}
