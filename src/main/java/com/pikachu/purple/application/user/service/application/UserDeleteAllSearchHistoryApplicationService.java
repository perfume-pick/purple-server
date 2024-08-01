package com.pikachu.purple.application.user.service.application;

import static com.pikachu.purple.support.security.SecurityProvider.getCurrentUserAuthentication;

import com.pikachu.purple.application.user.port.in.UserDeleteAllSearchHistoryUseCase;
import com.pikachu.purple.application.user.service.domain.UserSearchHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDeleteAllSearchHistoryApplicationService implements
    UserDeleteAllSearchHistoryUseCase {

    private final UserSearchHistoryService userSearchHistoryService;

    @Override
    public void invoke() {
        Long userId = getCurrentUserAuthentication().userId();
        userSearchHistoryService.deleteAllSearchHistory(userId);
    }

}
