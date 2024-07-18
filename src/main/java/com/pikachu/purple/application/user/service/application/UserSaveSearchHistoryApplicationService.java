package com.pikachu.purple.application.user.service.application;

import static com.pikachu.purple.support.security.SecurityProvider.getCurrentUserAuthentication;

import com.pikachu.purple.application.user.port.in.UserSaveSearchHistoryUseCase;
import com.pikachu.purple.application.user.service.domain.UserSearchHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserSaveSearchHistoryApplicationService implements UserSaveSearchHistoryUseCase {

    private final UserSearchHistoryService userSearchHistoryService;

    @Override
    public void invoke(
        String keyword,
        String searchAt
    ) {
        Long userId = getCurrentUserAuthentication().userId();
        userSearchHistoryService.saveSearchHistory(
            userId,
            keyword,
            searchAt
        );
    }
}
