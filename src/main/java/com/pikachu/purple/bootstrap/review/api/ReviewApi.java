package com.pikachu.purple.bootstrap.review.api;

import com.pikachu.purple.bootstrap.common.dto.SuccessResponse;
import com.pikachu.purple.bootstrap.common.security.Secured;
import com.pikachu.purple.bootstrap.review.dto.request.CreateReviewDetailRequest;
import com.pikachu.purple.bootstrap.review.dto.request.CreateReviewSimpleRequest;
import com.pikachu.purple.bootstrap.review.dto.request.UpdateReviewDetailRequest;
import com.pikachu.purple.bootstrap.review.dto.request.UpdateReviewSimpleRequest;
import com.pikachu.purple.bootstrap.review.dto.response.GetEvaluationFormFieldResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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
    @Operation(summary = "간단한 리뷰 작성")
    @PostMapping("/simple")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void createSimple(@RequestBody @Valid CreateReviewSimpleRequest request);

    @Secured
    @Operation(summary = "자세한 리뷰 작성")
    @PostMapping("/detail")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void createDetail(@RequestBody @Valid CreateReviewDetailRequest request);

    @Operation(
        summary = "자세한 리뷰 평가 항목 정보 조회",
        description = "지속력, 시야주, 계절감/시간, 성별, 분위기"
    )
    @GetMapping("/evaluation-form")
    @ResponseStatus(HttpStatus.OK)
    SuccessResponse<GetEvaluationFormFieldResponse> findEvaluationFormField();

    @Secured
    @Operation(
        summary = "간단한 리뷰 수정",
        description = "간단한 리뷰 내용 및 별점 수정"
    )
    @PatchMapping("/{review-id}/simple")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void updateSimple(
        @PathVariable("review-id") @NotNull @Positive Long reviewId,
        @RequestBody @Valid UpdateReviewSimpleRequest request
    );

    @Secured
    @Operation(summary = "자세한 리뷰 수정")
    @PatchMapping("/{review-id}/detail")
    void updateDetail(
        @PathVariable("review-id") @NotNull @Positive Long reviewId,
        @RequestBody @Valid UpdateReviewDetailRequest request
    );

    @Secured
    @Operation(summary = "리뷰 삭제")
    @DeleteMapping("/{review-id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@PathVariable("review-id") @NotNull @Positive Long reviewId);

    @Secured
    @Operation(summary = "리뷰 신고")
    @PostMapping("/{review-id}/complain")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void complain(@PathVariable("review-id") @NotNull @Positive Long reviewId);

    @Secured
    @Operation(summary = "리뷰 신고 취소")
    @DeleteMapping("/{review-id}/complain")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteComplain(@PathVariable("review-id") @NotNull @Positive Long reviewId);

    @Secured
    @Operation(summary = "리뷰 좋아요")
    @PostMapping("/{review-id}/like")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void like(@PathVariable("review-id") @NotNull @Positive Long reviewId);

    @Secured
    @Operation(summary = "리뷰 좋아요 취소")
    @DeleteMapping("/{review-id}/like")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void unLike(@PathVariable("review-id") @NotNull @Positive Long reviewId);

}
