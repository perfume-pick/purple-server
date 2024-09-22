package com.pikachu.purple.bootstrap.user.controller;

import com.pikachu.purple.application.history.port.in.searchhistory.DeleteSearchHistoriesUseCase;
import com.pikachu.purple.application.history.port.in.searchhistory.GetSearchHistoriesUseCase;
import com.pikachu.purple.application.user.port.in.user.UpdateProfileUseCase;
import com.pikachu.purple.bootstrap.common.dto.SuccessResponse;
import com.pikachu.purple.bootstrap.user.api.UserApi;
import com.pikachu.purple.bootstrap.user.dto.response.GetSearchHistoriesResponse;
import com.pikachu.purple.bootstrap.user.dto.response.GetUserProfileResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class UserController implements UserApi {

    private final UpdateProfileUseCase updateProfileUseCase;
    private final GetSearchHistoriesUseCase getSearchHistoriesUseCase;
    private final DeleteSearchHistoriesUseCase deleteSearchHistoriesUseCase;

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
    public SuccessResponse<GetSearchHistoriesResponse> findAllSearchHistory() {
        GetSearchHistoriesUseCase.Result result = getSearchHistoriesUseCase.invoke();

        return SuccessResponse.of(new GetSearchHistoriesResponse(result.searchHistories()));
    }

    @Override
    public void deleteAllSearchHistory() {
        deleteSearchHistoriesUseCase.invoke();
    }

}
