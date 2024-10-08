package com.pikachu.purple.application.review.service.application.complaint;

import com.pikachu.purple.application.review.common.dto.ComplaintFormDTO;
import com.pikachu.purple.application.review.port.in.complaint.GetComplaintFormUseCase;
import com.pikachu.purple.application.review.service.domain.ComplaintDomainService;
import com.pikachu.purple.application.review.service.domain.ReviewDomainService;
import com.pikachu.purple.domain.review.Complaint;
import com.pikachu.purple.domain.review.Review;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GetComplaintFormApplicationService implements GetComplaintFormUseCase {

    private final ComplaintDomainService complaintDomainService;
    private final ReviewDomainService reviewDomainService;

    @Value(value = "${uri.server-review}")
    private String reviewUri;

    @Transactional
    @Override
    public Result invoke(Command command) {
        Complaint complaint = complaintDomainService.find(
            command.complaintId(),
            command.token()
        );

        Review review = reviewDomainService.findWithPerfume(complaint.getReview().getId());

        ComplaintFormDTO complaintFormDTO = ComplaintFormDTO.from(
            review,
            complaint,
            reviewUri
        );

        return new Result(complaintFormDTO);
    }

}
