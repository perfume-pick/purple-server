package com.pikachu.purple.bootstrap.user.controller;

import static com.pikachu.purple.support.security.SecurityProvider.getCurrentUserAuthentication;

import com.pikachu.purple.application.history.port.in.searchhistory.CreateSearchHistoryUseCase;
import com.pikachu.purple.application.history.port.in.searchhistory.DeleteSearchHistoriesUseCase;
import com.pikachu.purple.application.history.port.in.searchhistory.GetSearchHistoriesUseCase;
import com.pikachu.purple.application.history.port.in.visithistory.CreateVisitHistoryUseCase;
import com.pikachu.purple.application.history.port.in.visithistory.DeleteVisitHistoriesUseCase;
import com.pikachu.purple.application.history.port.in.visithistory.GetVisitHistoriesUseCase;
import com.pikachu.purple.application.review.port.in.review.GetCurrentUserReviewUseCase;
import com.pikachu.purple.application.review.port.in.starrating.GetReviewsByUserAndSortTypeUseCase;
import com.pikachu.purple.application.user.port.in.user.DeleteUserUseCase;
import com.pikachu.purple.application.user.port.in.user.GetUserProfileByUserUseCase;
import com.pikachu.purple.application.review.port.in.review.GetTopThreeReviewedBrandsUseCase;
import com.pikachu.purple.application.review.port.in.review.GetUserReviewCountsUseCase;
import com.pikachu.purple.application.user.port.in.user.UpdateProfileUseCase;
import com.pikachu.purple.application.user.port.in.useraccord.GetPolarizedUserAccordsByUserUseCase;
import com.pikachu.purple.bootstrap.common.dto.SuccessResponse;
import com.pikachu.purple.bootstrap.user.api.UserApi;
import com.pikachu.purple.bootstrap.user.dto.response.GetPolarizedUserAccordsByUserResponse;
import com.pikachu.purple.bootstrap.user.dto.response.GetReviewByPerfumeIdAndUserResponse;
import com.pikachu.purple.bootstrap.user.dto.response.GetReviewsByUserAndSortTypeResponse;
import com.pikachu.purple.bootstrap.user.dto.response.GetSearchHistoriesResponse;
import com.pikachu.purple.bootstrap.user.dto.response.GetTopThreeReviewedBrandsResponse;
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
    private final CreateSearchHistoryUseCase createSearchHistoryUseCase;
    private final GetUserReviewCountsUseCase getUserReviewCountsUseCase;
    private final DeleteVisitHistoriesUseCase deleteVisitHistoriesUseCase;
    private final GetTopThreeReviewedBrandsUseCase getTopThreeReviewedBrandsUseCase;
    private final GetCurrentUserReviewUseCase getCurrentUserReviewUseCase;
    private final GetPolarizedUserAccordsByUserUseCase getPolarizedUserAccordsByUserUseCase;
    private final GetUserProfileByUserUseCase getUserProfileByUserUseCase;
    private final GetReviewsByUserAndSortTypeUseCase getReviewsByUserAndSortTypeUseCase;
    private final DeleteUserUseCase deleteUserUseCase;

    @Override
    public SuccessResponse<GetUserProfileResponse> updateProfile(
        String nickname,
        boolean isChanged,
        MultipartFile picture
    ) {
        UpdateProfileUseCase.Result result = updateProfileUseCase.invoke(
            nickname,
            isChanged,
            picture
        );

        return SuccessResponse.of(
            new GetUserProfileResponse(
                result.user().getNickname(),
                result.user().getImageUrl(),
                null
            )
        );
    }

    @Override
    public SuccessResponse<GetUserReviewCountsResponse> findCurrentUserReviewCounts() {
        GetUserReviewCountsUseCase.Result result = getUserReviewCountsUseCase.invoke();

        return SuccessResponse.of(new GetUserReviewCountsResponse(
            result.currentUserReviewCounts(),
            result.averageUserReviewCounts()
        ));
    }

    @Override
    public SuccessResponse<GetTopThreeReviewedBrandsResponse> findTopThreeReviewedBrands() {
        GetTopThreeReviewedBrandsUseCase.Result result = getTopThreeReviewedBrandsUseCase.invoke();

        return SuccessResponse.of(
            new GetTopThreeReviewedBrandsResponse(result.reviewedBrandDTOs()));
    }

    @Override
    public void createSearchHistory(String keyword) {
        Instant searchAt = Instant.now();
        createSearchHistoryUseCase.invoke(
            keyword,
            searchAt
        );
    }

    @Override
    public SuccessResponse<GetSearchHistoriesResponse> findAllSearchHistory() {
        GetSearchHistoriesUseCase.Result result = getSearchHistoriesUseCase.invoke();

        return SuccessResponse.of(new GetSearchHistoriesResponse(result.searchHistories()));
    }

    @Override
    public void deleteAllSearchHistory() {
        Long userId = getCurrentUserAuthentication().userId();

        deleteSearchHistoriesUseCase.deleteAll(userId);
    }

    @Override
    public void createVisitHistory(Long perfumeId) {
        Instant searchAt = Instant.now();

        createVisitHistoryUseCase.invoke(
            perfumeId,
            searchAt
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
        GetCurrentUserReviewUseCase.Result result = getCurrentUserReviewUseCase.invoke(perfumeId);

        return SuccessResponse.of(new GetReviewByPerfumeIdAndUserResponse(result.reviewByUserDTO()));
    }

    @Override
    public SuccessResponse<GetPolarizedUserAccordsByUserResponse> findPolarizedUserAccordsByUser() {
        GetPolarizedUserAccordsByUserUseCase.Result result = getPolarizedUserAccordsByUserUseCase.invoke();

        return SuccessResponse.of(new GetPolarizedUserAccordsByUserResponse(result.polarizedUserAccordDTO()));
    }

    @Override
    public SuccessResponse<GetUserProfileResponse> findUserProfileByUser() {
        GetUserProfileByUserUseCase.Result result = getUserProfileByUserUseCase.invoke();

        return SuccessResponse.of(
            new GetUserProfileResponse(
                result.nickname(),
                result.imageUrl(),
                result.email()
        ));
    }

    @Override
    public SuccessResponse<GetReviewsByUserAndSortTypeResponse> findAllReviewByUserAndSortType(String sortType) {
        GetReviewsByUserAndSortTypeUseCase.Result result = getReviewsByUserAndSortTypeUseCase.invoke(sortType);

        return SuccessResponse.of(
            new GetReviewsByUserAndSortTypeResponse(result.reviewWithPerfumeDTOs())
        );
    }

    @Override
    public void withdraw() {
        deleteUserUseCase.invoke();
    }

}
