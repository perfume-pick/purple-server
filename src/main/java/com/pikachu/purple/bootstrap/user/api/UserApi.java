package com.pikachu.purple.bootstrap.user.api;

import com.pikachu.purple.bootstrap.common.security.Secured;
import com.pikachu.purple.bootstrap.user.dto.request.RatingRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;

@Tag(name = "User", description = "User API")
@RequestMapping(value = "/perpicks/users", produces = "application/json")
public interface UserApi {

    @Secured
    @Operation(summary = "온보딩에서 향수에 대한 별점 저장")
    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void saveRating(@RequestBody RatingRequest request);

    @Secured
    @Operation(summary = "프로필 수정")
    @ApiResponses(
        value = {
            @ApiResponse(
                responseCode = "204", description = "프로필 수정 성공"
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
    @PatchMapping(value = "/profile", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void updateProfile(
        @RequestParam String nickname,
        @RequestPart(required = false) MultipartFile picture
    );

}
