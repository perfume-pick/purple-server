package com.pikachu.purple.bootstrap.review.api;

import com.pikachu.purple.bootstrap.common.dto.SuccessResponse;
import com.pikachu.purple.bootstrap.common.security.Secured;
import com.pikachu.purple.bootstrap.review.dto.request.CreateReviewSimpleRequest;
import com.pikachu.purple.bootstrap.review.dto.request.UpdateReviewSimpleRequest;
import com.pikachu.purple.bootstrap.review.dto.response.GetEvaluationFormFieldResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Tag(name = "Review", description = "Review API")
@RequestMapping(value = "/perpicks/reviews", produces = "application/json")
public interface ReviewApi {

    @Secured
    @Operation(summary = "간단한 코멘트 작성")
    @PostMapping("/simple")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void createSimple(@RequestBody @Valid CreateReviewSimpleRequest request);

    @Operation(
        summary = "자세한 리뷰 평가 항목 정보 조회",
        description = "지속력, 시야주, 계절감/시간, 성별, 분위기"
    )
    @GetMapping("/evaluation-fields")
    @ResponseStatus(HttpStatus.OK)
    SuccessResponse<GetEvaluationFormFieldResponse> findEvaluationFormField();

    @Secured
    @Operation(
        summary = "간단한 코멘트 수정",
        description = "간단한 코멘트 내용 및 별점 수정"
    )
    @PatchMapping("/simple/{review-id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void update(
        @PathVariable("review-id") Long reviewId,
        @RequestBody @Valid UpdateReviewSimpleRequest request
    );

    @Secured
    @Operation(summary = "리뷰 삭제")
    @DeleteMapping("/{review-id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@PathVariable("review-id") Long reviewId);

}
