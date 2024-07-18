package com.pikachu.purple.application.user.service.application;

import static com.pikachu.purple.support.security.SecurityProvider.getCurrentUserAuthentication;

import com.pikachu.purple.application.user.port.in.UserGetSearchHistoryUseCase;
import com.pikachu.purple.application.user.service.domain.UserSearchHistoryService;
import com.pikachu.purple.domain.user.entity.UserSearchHistory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserGetSearchHistoryApplicationService implements UserGetSearchHistoryUseCase {

    private final UserSearchHistoryService userSearchHistoryService;

    @Override
    public Result invoke() {
        Long userId = getCurrentUserAuthentication().userId();

        List<UserSearchHistory> userSearchHistories = userSearchHistoryService.findSearchHistoryByUserId(userId);

        return new Result(userSearchHistories);
    }

}
