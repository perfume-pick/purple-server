package com.pikachu.purple.bootstrap.complaint.controller;

import com.pikachu.purple.application.review.port.in.complaint.DeleteComplaintUseCase;
import com.pikachu.purple.application.review.port.in.complaint.GetComplaintFormUseCase;
import com.pikachu.purple.bootstrap.complaint.api.ComplaintApi;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
@RequiredArgsConstructor
public class ComplaintController implements ComplaintApi {

    private final GetComplaintFormUseCase getComplaintFormUseCase;
    private final DeleteComplaintUseCase deleteComplaintUseCase;

    @Override
    public String checkingPage(
        Long complaintId,
        String token,
        Model model
    ) {
        GetComplaintFormUseCase.Result result = getComplaintFormUseCase.invoke(
            complaintId,
            token
        );

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
       deleteComplaintUseCase.deleteWithReview(
           complaintId,
           token
       );
    }

}
