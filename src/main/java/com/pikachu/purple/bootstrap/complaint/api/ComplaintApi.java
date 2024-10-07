package com.pikachu.purple.bootstrap.complaint.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = "Complaint", description = "Complaint API")
@RequestMapping(value = "/perpicks/complaints", produces = "application/json")
public interface ComplaintApi {

    @Operation(
        summary = "리뷰 신고내역 확인 및 처리"
    )
    @GetMapping("/{complaint-id}")
    String findComplaint(
        @PathVariable("complaint-id") Long complaintId,
        @RequestParam String token,
        Model model
    );

}
