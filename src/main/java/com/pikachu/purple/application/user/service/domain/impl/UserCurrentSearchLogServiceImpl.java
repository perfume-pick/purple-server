package com.pikachu.purple.application.user.service.domain.impl;

import com.pikachu.purple.application.user.port.out.UserCurrentSearchLogRepository;
import com.pikachu.purple.application.user.service.domain.UserCurrentSearchLogService;
import com.pikachu.purple.domain.user.entity.UserCurrentSearchLog;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserCurrentSearchLogServiceImpl implements UserCurrentSearchLogService {

    private final UserCurrentSearchLogRepository userCurrentSearchLogRepository;

    @Override
    public List<UserCurrentSearchLog> findCurrentSearchLogByUserId(Long userId) {
       return userCurrentSearchLogRepository.findCurrentSearchLogByUserId(userId);
    }

    @Override
    public void saveCurrentSearchLog(
        Long userId,
        String keyword,
        String searchAt
    ) {
        userCurrentSearchLogRepository.saveCurrentSearchLog(
            userId,
            keyword,
            searchAt
        );
    }

    @Override
    public void deleteAllCurrentSearchLog(Long userId) {
        userCurrentSearchLogRepository.deleteAllCurrentSearchLog(userId);
    }

}
