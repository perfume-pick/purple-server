package com.pikachu.purple.bootstrap.batch.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Tag(name = "Batch", description = "Batch API")
@RequestMapping(value = "/perpicks/batch", produces = "application/json")
public interface BatchApi {

//    @Operation(
//        summary = "별점 집계",
//        description = "수동 별점 집계 API"
//    )
//    @GetMapping("/star-rating-statistics")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    void countStarRatingStatistics();

    @Operation(
        summary = "평가 집계",
        description = "수동 평가 집계 API"
    )
    @GetMapping("/evaluation-statistics")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void countEvaluationStatistics();


}
