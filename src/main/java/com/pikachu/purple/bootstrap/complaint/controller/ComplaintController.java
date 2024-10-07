package com.pikachu.purple.bootstrap.complaint.controller;

import com.pikachu.purple.application.review.port.in.complaint.GetComplaintFormUseCase;
import com.pikachu.purple.bootstrap.complaint.api.ComplaintApi;
import java.time.Instant;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ComplaintController implements ComplaintApi {

    private final GetComplaintFormUseCase getComplaintFormUseCase;

    @Override
    public String findComplaint(
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
        model.addAttribute("adminLink", result.complaintFormDTO().link());

        return "report-complaint";
    }

}
