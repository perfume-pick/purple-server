package com.pikachu.purple.application.review.util;

import com.pikachu.purple.domain.review.Review;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ComplaintFormConverter {

    public static Map<String, Object> of(
        Long reporterId,
        Review reportedReview
    ) {
        Map<String, Object> complaintForm = new HashMap<>();

        Instant reportAt = Instant.now();

        complaintForm.put("reportDate", reportAt);
        complaintForm.put("reporterId", reporterId);
        complaintForm.put("reportedId", reportedReview.getUser().getId());
        complaintForm.put("reportedPerfumeId", reportedReview.getPerfume().getId());
        complaintForm.put("reportedPerfumeName", reportedReview.getPerfume().getName());
        complaintForm.put("reportedComment", reportedReview.getContent());
        complaintForm.put("adminLink", "");

        return complaintForm;
    }

}
