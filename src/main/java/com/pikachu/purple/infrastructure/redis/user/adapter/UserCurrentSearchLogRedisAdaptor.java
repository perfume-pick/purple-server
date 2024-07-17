package com.pikachu.purple.infrastructure.redis.user.adapter;

import com.pikachu.purple.application.user.port.out.UserCurrentSearchLogRepository;
import com.pikachu.purple.domain.user.entity.UserCurrentSearchLog;
import com.pikachu.purple.infrastructure.redis.user.entity.UserCurrentSearchLogRedisHash;
import com.pikachu.purple.infrastructure.redis.user.repository.UserCurrentSearchLogRedisRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserCurrentSearchLogRedisAdaptor implements UserCurrentSearchLogRepository {

    private final UserCurrentSearchLogRedisRepository userCurrentSearchLogRedisRepository;

    @Override
    public List<UserCurrentSearchLog> findCurrentSearchLogByUserId(Long userId) {
        List<UserCurrentSearchLogRedisHash> currentSearchLogRedisHashes = userCurrentSearchLogRedisRepository.findCurrentSearchLogByUserId(userId);

        return currentSearchLogRedisHashes.stream()
            .map(UserCurrentSearchLogRedisHash::toDomain)
            .collect(Collectors.toList());
    }

    @Override
    public void saveCurrentSearchLog(
        Long userId,
        String keyword,
        String searchAt
    ) {
        UserCurrentSearchLogRedisHash userCurrentSearchLogRedisHash = UserCurrentSearchLogRedisHash.builder()
            .id(userId)
            .searchName(keyword)
            .searchAt(searchAt)
            .build();

        userCurrentSearchLogRedisRepository.saveCurrentSearchLog(
            userId,
            userCurrentSearchLogRedisHash
        );
    }

    @Override
    public void deleteAllCurrentSearchLog(Long userId) {
        userCurrentSearchLogRedisRepository.deleteAllCurrentSearchLog(userId);
    }

}
