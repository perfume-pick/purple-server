package com.pikachu.purple.bootstrap.user.controller;

import com.pikachu.purple.application.history.port.in.searchhistory.DeleteSearchHistoriesUseCase;
import com.pikachu.purple.application.history.port.in.searchhistory.GetSearchHistoriesUseCase;
import com.pikachu.purple.application.history.port.in.visithistory.CreateVisitHistoryUseCase;
import com.pikachu.purple.application.history.port.in.visithistory.DeleteVisitHistoriesUseCase;
import com.pikachu.purple.application.history.port.in.visithistory.GetVisitHistoriesUseCase;
import com.pikachu.purple.application.review.port.in.review.GetReviewByPerfumeIdAndUserUseCase;
import com.pikachu.purple.application.review.port.in.review.GetUserReviewCountsUseCase;
import com.pikachu.purple.application.user.port.in.user.UpdateProfileUseCase;
import com.pikachu.purple.application.user.port.in.useraccord.GetPolarizedUserAccordsByUserUseCase;
import com.pikachu.purple.bootstrap.common.dto.SuccessResponse;
import com.pikachu.purple.bootstrap.user.api.UserApi;
import com.pikachu.purple.bootstrap.user.dto.response.GetPolarizedUserAccordsByUserResponse;
import com.pikachu.purple.bootstrap.user.dto.response.GetReviewByPerfumeIdAndUserResponse;
import com.pikachu.purple.bootstrap.user.dto.response.GetSearchHistoriesResponse;
import com.pikachu.purple.bootstrap.user.dto.response.GetUserProfileResponse;
import com.pikachu.purple.bootstrap.user.dto.response.GetUserReviewCountsResponse;
import com.pikachu.purple.bootstrap.user.dto.response.GetVisitHistoriesResponse;
import java.time.Instant;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class UserController implements UserApi {

    private final UpdateProfileUseCase updateProfileUseCase;
    private final GetSearchHistoriesUseCase getSearchHistoriesUseCase;
    private final DeleteSearchHistoriesUseCase deleteSearchHistoriesUseCase;
    private final CreateVisitHistoryUseCase createVisitHistoryUseCase;
    private final GetVisitHistoriesUseCase getVisitHistoriesUseCase;
    private final GetUserReviewCountsUseCase getUserReviewCountsUseCase;
    private final DeleteVisitHistoriesUseCase deleteVisitHistoriesUseCase;
    private final GetReviewByPerfumeIdAndUserUseCase getReviewByPerfumeIdAndUserUseCase;
    private final GetPolarizedUserAccordsByUserUseCase getPolarizedUserAccordsByUserUseCase;

    @Override
    public SuccessResponse<GetUserProfileResponse> updateProfile(
        String nickname,
        boolean isChanged,
        MultipartFile picture
    ) {
        UpdateProfileUseCase.Result result = updateProfileUseCase.invoke(
            new UpdateProfileUseCase.Command(
                nickname,
                isChanged,
                picture
            )
        );

        return SuccessResponse.of(
            new GetUserProfileResponse(
                result.user().getNickname(),
                result.user().getImageUrl()
            )
        );
    }

    @Override
    public SuccessResponse<GetUserReviewCountsResponse> findCurrentUserReviewCounts() {
        GetUserReviewCountsUseCase.Result result = getUserReviewCountsUseCase.invoke();

        return SuccessResponse.of(new GetUserReviewCountsResponse(result.userReviewCountsDTO()));
    }

    @Override
    public SuccessResponse<GetSearchHistoriesResponse> findAllSearchHistory() {
        GetSearchHistoriesUseCase.Result result = getSearchHistoriesUseCase.invoke();

        return SuccessResponse.of(new GetSearchHistoriesResponse(result.searchHistories()));
    }

    @Override
    public void deleteAllSearchHistory() {
        deleteSearchHistoriesUseCase.invoke();
    }

    @Override
    public void createVisitHistory(Long perfumeId) {
        Instant searchAt = Instant.now();

        createVisitHistoryUseCase.invoke(new CreateVisitHistoryUseCase.Command(
            perfumeId,
            searchAt
            )
        );
    }

    @Override
    public SuccessResponse<GetVisitHistoriesResponse> findAllVisitHistory() {
        GetVisitHistoriesUseCase.Result result = getVisitHistoriesUseCase.invoke();

        return SuccessResponse.of(new GetVisitHistoriesResponse(result.visitHistoryDTOs()));
    }

    @Override
    public void deleteAllVisitHistory() {
        deleteVisitHistoriesUseCase.invoke();
    }

    @Override
    public SuccessResponse<GetReviewByPerfumeIdAndUserResponse> findReviewByPerfumeIdAndUser(
        Long perfumeId) {
        GetReviewByPerfumeIdAndUserUseCase.Result result = getReviewByPerfumeIdAndUserUseCase.invoke(
            new GetReviewByPerfumeIdAndUserUseCase.Command(perfumeId));

        return SuccessResponse.of(new GetReviewByPerfumeIdAndUserResponse(result.reviewByUserDTO()));
    }

    @Override
    public SuccessResponse<GetPolarizedUserAccordsByUserResponse> findPolarizedUserAccordsByUser() {
        GetPolarizedUserAccordsByUserUseCase.Result result = getPolarizedUserAccordsByUserUseCase.invoke();

        return SuccessResponse.of(new GetPolarizedUserAccordsByUserResponse(result.polarizedUserAccordDTO()));
    }

}
