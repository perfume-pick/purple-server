package com.pikachu.purple.bootstrap.user.controller;

import static com.pikachu.purple.support.security.SecurityProvider.getCurrentUserAuthentication;

import com.pikachu.purple.application.history.port.in.searchhistory.CreateSearchHistoryUseCase;
import com.pikachu.purple.application.history.port.in.searchhistory.DeleteSearchHistoriesUseCase;
import com.pikachu.purple.application.history.port.in.searchhistory.GetSearchHistoriesUseCase;
import com.pikachu.purple.application.history.port.in.visithistory.CreateVisitHistoryUseCase;
import com.pikachu.purple.application.history.port.in.visithistory.DeleteVisitHistoriesUseCase;
import com.pikachu.purple.application.perfume.port.in.GetVisitedPerfumesUseCase;
import com.pikachu.purple.application.review.port.in.review.GetCurrentAndAverageUserReviewCountsUseCase;
import com.pikachu.purple.application.review.port.in.review.GetReviewUseCase;
import com.pikachu.purple.application.review.port.in.review.GetReviewsUseCase;
import com.pikachu.purple.application.review.port.in.review.GetTopThreeReviewedBrandsUseCase;
import com.pikachu.purple.application.user.port.in.user.GetUserUseCase;
import com.pikachu.purple.application.user.port.in.user.UpdateProfileUseCase;
import com.pikachu.purple.application.user.port.in.user.WithdrawUserUseCase;
import com.pikachu.purple.application.user.port.in.useraccord.GetPolarizedUserAccordsUseCase;
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
    private final GetVisitedPerfumesUseCase getVisitedPerfumesUseCase;
    private final CreateSearchHistoryUseCase createSearchHistoryUseCase;
    private final GetCurrentAndAverageUserReviewCountsUseCase getCurrentAndAverageUserReviewCountsUseCase;
    private final DeleteVisitHistoriesUseCase deleteVisitHistoriesUseCase;
    private final GetTopThreeReviewedBrandsUseCase getTopThreeReviewedBrandsUseCase;
    private final GetReviewUseCase getReviewUseCase;
    private final GetPolarizedUserAccordsUseCase getPolarizedUserAccordsUseCase;
    private final GetReviewsUseCase getReviewsUseCase;
    private final WithdrawUserUseCase withdrawUserUseCase;
    private final GetUserUseCase getUserUseCase;

    @Override
    public SuccessResponse<GetUserProfileResponse> updateProfile(
        String nickname,
        boolean isChanged,
        MultipartFile picture
    ) {
        Long userId = getCurrentUserAuthentication().userId();
        UpdateProfileUseCase.Result result = updateProfileUseCase.update(
            userId,
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
        Long userId = getCurrentUserAuthentication().userId();

        GetCurrentAndAverageUserReviewCountsUseCase.Result result =
            getCurrentAndAverageUserReviewCountsUseCase.find(userId);

        return SuccessResponse.of(
            GetUserReviewCountsResponse.of(result)
        );
    }

    @Override
    public SuccessResponse<GetTopThreeReviewedBrandsResponse> findTopThreeReviewedBrands() {
        GetTopThreeReviewedBrandsUseCase.Result result = getTopThreeReviewedBrandsUseCase.invoke();

        return SuccessResponse.of(
            GetTopThreeReviewedBrandsResponse.of(result)
        );
    }

    @Override
    public void createSearchHistory(String keyword) {
        Long userId = getCurrentUserAuthentication().userId();
        Instant searchAt = Instant.now();

        createSearchHistoryUseCase.create(
            userId,
            keyword,
            searchAt
        );
    }

    @Override
    public SuccessResponse<GetSearchHistoriesResponse> findAllSearchHistory() {
        Long userId = getCurrentUserAuthentication().userId();
        GetSearchHistoriesUseCase.Result result = getSearchHistoriesUseCase.findAll(userId);

        return SuccessResponse.of(
            GetSearchHistoriesResponse.of(result)
        );
    }

    @Override
    public void deleteAllSearchHistory() {
        Long userId = getCurrentUserAuthentication().userId();

        deleteSearchHistoriesUseCase.deleteAll(userId);
    }

    @Override
    public void createVisitHistory(Long perfumeId) {
        Long userId = getCurrentUserAuthentication().userId();
        Instant searchAt = Instant.now();

        createVisitHistoryUseCase.create(
            userId,
            perfumeId,
            searchAt
        );
    }

    @Override
    public SuccessResponse<GetVisitHistoriesResponse> findAllVisitHistory() {
        Long userId = getCurrentUserAuthentication().userId();
        GetVisitedPerfumesUseCase.Result result = getVisitedPerfumesUseCase.findAllWithPerfumeAccord(userId);

        return SuccessResponse.of(
            GetVisitHistoriesResponse.of(result)
        );
    }

    @Override
    public void deleteAllVisitHistory() {
        Long userId = getCurrentUserAuthentication().userId();

        deleteVisitHistoriesUseCase.deleteAll(userId);
    }

    @Override
    public SuccessResponse<GetReviewByPerfumeIdAndUserResponse> findReviewByPerfumeIdAndUser(
        Long perfumeId) {
        Long userId = getCurrentUserAuthentication().userId();

        GetReviewUseCase.Result result = getReviewUseCase.findWithStarRatingAndEvaluationAndMoods(userId, perfumeId);

        return SuccessResponse.of(
            GetReviewByPerfumeIdAndUserResponse.of(result)
        );
    }

    @Override
    public SuccessResponse<GetPolarizedUserAccordsByUserResponse> findPolarizedUserAccordsByUser() {
        Long userId = getCurrentUserAuthentication().userId();
        GetPolarizedUserAccordsUseCase.Result result = getPolarizedUserAccordsUseCase.find(userId);

        return SuccessResponse.of(new GetPolarizedUserAccordsByUserResponse(result.polarizedUserAccordDTO()));
    }

    @Override
    public SuccessResponse<GetUserProfileResponse> findUserProfileByUser() {
        Long userId = getCurrentUserAuthentication().userId();
        GetUserUseCase.Result result = getUserUseCase.find(userId);

        return SuccessResponse.of(
            new GetUserProfileResponse(
                result.user().getNickname(),
                result.user().getImageUrl(),
                result.user().getEmail()
        ));
    }

    @Override
    public SuccessResponse<GetReviewsByUserAndSortTypeResponse> findAllReviewByUserAndSortType(String sortType) {
        Long userId = getCurrentUserAuthentication().userId();
        GetReviewsUseCase.Result result = getReviewsUseCase
            .findAllWithPerfumeAndReviewEvaluationAndMoodsAndIsLiked(
                userId,
                sortType
            );

        return SuccessResponse.of(
            GetReviewsByUserAndSortTypeResponse.of(result)
        );
    }

    @Override
    public void withdraw() {
        Long userId = getCurrentUserAuthentication().userId();

        withdrawUserUseCase.withdraw(userId);
    }

}
