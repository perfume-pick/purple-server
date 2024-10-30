package com.pikachu.purple.bootstrap.user.api;

import com.pikachu.purple.bootstrap.common.dto.SuccessResponse;
import com.pikachu.purple.bootstrap.common.security.Secured;
import com.pikachu.purple.bootstrap.user.dto.response.GetPolarizedUserAccordsByUserResponse;
import com.pikachu.purple.bootstrap.user.dto.response.GetReviewByPerfumeIdAndUserResponse;
import com.pikachu.purple.bootstrap.user.dto.response.GetVisitHistoriesResponse;
import com.pikachu.purple.bootstrap.user.dto.response.GetSearchHistoriesResponse;
import com.pikachu.purple.bootstrap.user.dto.response.GetUserProfileResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;

@Tag(name = "User", description = "User API")
@RequestMapping(value = "/perpicks/users", produces = "application/json")
public interface UserApi {

    @Secured
    @Operation(summary = "프로필 수정")
    @ApiResponses(
        value = {
            @ApiResponse(
                responseCode = "200", description = "프로필 수정 성공 및 반환"
            ),
            @ApiResponse(
                responseCode = "404", description = "U003: 사용자를 찾을 수 없습니다."
            ),
            @ApiResponse(
                responseCode = "409", description = "U004: 이미 사용중인 닉네임입니다."
            ),
            @ApiResponse(
                responseCode = "400-A", description = "S001: 업로드 파일이 비어있습니다."
            ),
            @ApiResponse(
                responseCode = "400-B", description = "S002: 파일을 업로드하는데 실패하였습니다."
            ),
            @ApiResponse(
                responseCode = "400-C", description = "S003: 파일 확장자명이 올바르지 않습니다."
            ),
            @ApiResponse(
                responseCode = "400-D", description = "S004: 파일을 삭제하는데 실패했습니다."
            ),
        }
    )
    @PatchMapping(value = "/my/profile", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @ResponseStatus(HttpStatus.OK)
    SuccessResponse<GetUserProfileResponse> updateProfile(
        @RequestParam String nickname,
        @RequestParam boolean isChanged,
        @RequestPart(required = false) MultipartFile picture
    );

    @Secured
    @Operation(summary = "최근 검색 기록 전체 조회")
    @GetMapping("/my/search-histories")
    @ResponseStatus(HttpStatus.OK)
    SuccessResponse<GetSearchHistoriesResponse> findAllSearchHistory();

    @Secured
    @Operation(summary = "최근 검색 기록 전체 삭제")
    @DeleteMapping("/my/search-histories")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteAllSearchHistory();

    @Secured
    @Operation(summary = "최근 본 상품 기록 저장")
    @PostMapping("/my/visit-histories/{perfume-id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void createVisitHistory(@PathVariable("perfume-id") Long perfumeId);

    @Secured
    @Operation(summary = "최근 본 상품 전체 조회")
    @GetMapping("/my/visit-histories")
    @ResponseStatus(HttpStatus.OK)
    SuccessResponse<GetVisitHistoriesResponse> findAllVisitHistory();

    @Secured
    @Operation(summary = "최근 본 상품 전체 삭제")
    @DeleteMapping("/my/visit-histories")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteAllVisitHistory();

    @Secured
    @Operation(
        summary = "리뷰 조회",
        description = "향수 상세 페이지 별점 조회"
    )
    @GetMapping("/my/perfumes/{perfume-id}/reviews")
    @ResponseStatus(HttpStatus.OK)
    SuccessResponse<GetReviewByPerfumeIdAndUserResponse> findReviewByPerfumeIdAndUser(@PathVariable("perfume-id") Long perfumeId);

    @Secured
    @Operation(
        summary = "선호 불호 어코드 조회",
        description = "마이페이지 선호/불호 어코드 조회"
    )
    @GetMapping("/my/user-accords")
    @ResponseStatus(HttpStatus.OK)
    SuccessResponse<GetPolarizedUserAccordsByUserResponse> findPolarizedUserAccordsByUser();

    @Secured
    @Operation(
        summary = "사용자 정보 반환",
        description = "닉네임, 프로필 사진, 이메일"
    )
    @GetMapping("/my/profile")
    @ResponseStatus(HttpStatus.OK)
    SuccessResponse<GetUserProfileResponse> findUserProfileByUser();

}
