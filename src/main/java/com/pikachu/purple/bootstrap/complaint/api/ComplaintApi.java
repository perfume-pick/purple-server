package com.pikachu.purple.bootstrap.complaint.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

@Tag(name = "Complaint", description = "Complaint API")
@RequestMapping(value = "/perpicks/complaints", produces = "application/json")
public interface ComplaintApi {

    @Operation(
        summary = "신고내역 확인"
    )
    @GetMapping("/{complaint-id}")
    String find(
        @PathVariable("complaint-id") Long complaintId,
        @RequestParam String token,
        Model model
    );

    @Operation(
        summary = "신고내역 삭제"
    )
    @DeleteMapping("/{complaint-id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@PathVariable
        ("complaint-id") Long complaintId,
        @RequestParam String token
    );

}
