package com.pikachu.purple.infrastructure.redis.user.adapter;

import com.pikachu.purple.application.user.port.out.UserSearchHistoryRepository;
import com.pikachu.purple.domain.user.entity.UserSearchHistory;
import com.pikachu.purple.infrastructure.redis.user.entity.UserSearchHistoryRedisHash;
import com.pikachu.purple.infrastructure.redis.user.repository.UserSearchHistoryRedisRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserSearchHistoryRedisAdaptor implements UserSearchHistoryRepository {

    private final UserSearchHistoryRedisRepository userSearchHistoryRedisRepository;

    @Override
    public List<UserSearchHistory> findSearchHistoryByUserId(Long userId) {
        List<UserSearchHistoryRedisHash> searchHistoryRedisHashes = userSearchHistoryRedisRepository.findSearchHistoryByUserId(userId);

        return searchHistoryRedisHashes.stream()
            .map(UserSearchHistoryRedisHash::toDomain)
            .collect(Collectors.toList());
    }

    @Override
    public void saveSearchHistory(
        Long userId,
        String keyword,
        String searchAt
    ) {
        UserSearchHistoryRedisHash userSearchHistoryRedisHash = UserSearchHistoryRedisHash.builder()
            .id(userId)
            .searchName(keyword)
            .searchAt(searchAt)
            .build();

        userSearchHistoryRedisRepository.saveSearchHistory(
            userId,
            userSearchHistoryRedisHash
        );
    }

    @Override
    public void deleteAllSearchHistory(Long userId) {
        userSearchHistoryRedisRepository.deleteAllSearchHistory(userId);
    }

}
