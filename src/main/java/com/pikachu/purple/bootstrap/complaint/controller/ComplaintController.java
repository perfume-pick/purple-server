package com.pikachu.purple.bootstrap.complaint.controller;

import com.pikachu.purple.application.review.port.in.complaint.DeleteComplaintWithReviewUseCase;
import com.pikachu.purple.application.review.port.in.complaint.GetComplaintFormUseCase;
import com.pikachu.purple.bootstrap.complaint.api.ComplaintApi;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
@RequiredArgsConstructor
public class ComplaintController implements ComplaintApi {

    private final GetComplaintFormUseCase getComplaintFormUseCase;
    private final DeleteComplaintWithReviewUseCase deleteComplaintWithReviewUseCase;

    @Override
    public String find(
        Long complaintId,
        String token,
        Model model
    ) {
        GetComplaintFormUseCase.Result result = getComplaintFormUseCase.invoke(new GetComplaintFormUseCase.Command(
            complaintId,
            token
        ));

        model.addAttribute("reportDate", result.complaintFormDTO().reportedAt());
        model.addAttribute("reporterId", result.complaintFormDTO().reporterId());
        model.addAttribute("reportedId", result.complaintFormDTO().reportedId());
        model.addAttribute("reportedPerfumeId", result.complaintFormDTO().perfumeId());
        model.addAttribute("reportedPerfumeName", result.complaintFormDTO().perfumeName());
        model.addAttribute("reportedComment", result.complaintFormDTO().comment());
        model.addAttribute("link", result.complaintFormDTO().link());

        return "report-complaint";
    }

    @Override
    public void delete(
        Long complaintId,
        String token
    ) {
       deleteComplaintWithReviewUseCase.invoke(new DeleteComplaintWithReviewUseCase.Command(
           complaintId,
           token
       ));
    }

}
