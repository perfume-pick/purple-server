package com.pikachu.purple.application.user.service.domain.impl;

import com.pikachu.purple.application.user.port.out.UserSearchHistoryRepository;
import com.pikachu.purple.application.user.service.domain.UserSearchHistoryService;
import com.pikachu.purple.domain.user.UserSearchHistory;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserSearchHistoryServiceImpl implements UserSearchHistoryService {

    private final UserSearchHistoryRepository userSearchHistoryRepository;

    @Override
    public List<UserSearchHistory> findSearchHistoryByUserId(Long userId) {
       return userSearchHistoryRepository.findSearchHistoryByUserId(userId);
    }

    @Override
    public void saveSearchHistory(
        Long userId,
        String keyword,
        LocalDateTime searchAt
    ) {
        userSearchHistoryRepository.saveSearchHistory(
            userId,
            keyword,
            searchAt
        );
    }

    @Override
    public void deleteAllSearchHistory(Long userId) {
        userSearchHistoryRepository.deleteAllSearchHistory(userId);
    }

}
