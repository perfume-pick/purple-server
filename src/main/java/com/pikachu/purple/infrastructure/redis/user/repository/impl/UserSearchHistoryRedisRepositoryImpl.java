package com.pikachu.purple.infrastructure.redis.user.repository.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pikachu.purple.infrastructure.redis.user.entity.SearchHistoryRedisHash;
import com.pikachu.purple.infrastructure.redis.user.repository.UserSearchHistoryRedisRepository;
import java.util.Collections;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
class UserSearchHistoryRedisRepositoryImpl implements
    UserSearchHistoryRedisRepository {

    private static final int MAX_SIZE = 9;
    private static final String KEY = "search_history:";

    private final RedisTemplate<String, Object> redisTemplate;
    private final ObjectMapper objectMapper;

    @Override
    public List<SearchHistoryRedisHash> findSearchHistoryByUserId(Long userId){
        List<Object> result = redisTemplate.opsForList().range(
            KEY + userId,
            0,
            MAX_SIZE
        );

        if (result == null) {
            return Collections.emptyList();
        }

        return result.stream()
            .map(object -> objectMapper.convertValue(
                object,
                SearchHistoryRedisHash.class
            ))
            .toList();
    }

    @Override
    public void saveSearchHistory(SearchHistoryRedisHash searchHistoryRedisHash) {
        Long getCount = redisTemplate.opsForList().size(KEY + searchHistoryRedisHash.getId());
        redisTemplate.opsForList().leftPush(
            KEY + searchHistoryRedisHash.getId(),
            searchHistoryRedisHash
        );
        if(getCount != null && getCount >= 10) redisTemplate.opsForList().trim(
            KEY + searchHistoryRedisHash.getId(),
            0,
            MAX_SIZE
        );
    }

    @Override
    public void deleteAllSearchHistory(Long userId) {
        redisTemplate.delete(KEY + userId);
    }

    @Override
    public void validateNotExist(Long userId, String keyword) {
        List<Object> result = redisTemplate.opsForList().range(
            KEY + userId,
            0,
            MAX_SIZE
        );

        if (result == null || result.isEmpty()) {
            return;
        }

        for(Object object : result) {
            SearchHistoryRedisHash hash = objectMapper.convertValue(object, SearchHistoryRedisHash.class);
            if(hash.getKeyword().equals(keyword)) {
                redisTemplate.opsForList().remove(
                    KEY + userId,
                    0,
                    hash
                );
            }
        }
    }

}
