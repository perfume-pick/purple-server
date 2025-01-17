package com.pikachu.purple.infrastructure.redis.user.repository.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pikachu.purple.infrastructure.redis.user.entity.VisitHistoryRedisHash;
import com.pikachu.purple.infrastructure.redis.user.repository.VisitHistoryRedisRepository;
import java.util.Collections;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class VisitHistoryRedisRepositoryImpl implements VisitHistoryRedisRepository {

    private static final int MAX_SIZE = 9;
    private static final String KEY = "visitHistory:";

    private final RedisTemplate<String, Object> redisTemplate;
    private final ObjectMapper objectMapper;

    @Override
    public void save(VisitHistoryRedisHash visitHistoryRedisHash) {
        Long getCount = redisTemplate.opsForList().size(KEY + visitHistoryRedisHash.getId());
        redisTemplate.opsForList().leftPush(
            KEY + visitHistoryRedisHash.getId(),
            visitHistoryRedisHash
        );

        if(getCount != null && getCount >= 10) {
            redisTemplate.opsForList().trim(
                KEY + visitHistoryRedisHash.getId(),
                0,
                MAX_SIZE
            );
        }
    }

    @Override
    public List<VisitHistoryRedisHash> findAllByUserId(Long userId) {
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
                VisitHistoryRedisHash.class
            ))
            .toList();
    }

    @Override
    public void deleteAllVisitHistory(Long userId) {
        redisTemplate.delete(KEY + userId);
    }

    @Override
    public void validateNotExist(Long userId, Long perfumeId) {
        List<Object> result = redisTemplate.opsForList().range(
            KEY + userId,
            0,
            MAX_SIZE
        );

        if (result == null || result.isEmpty()) {
            return;
        }

        List<Object> updatedList = result.stream()
            .filter(object -> {
                VisitHistoryRedisHash hash = objectMapper.convertValue(
                    object,
                    VisitHistoryRedisHash.class
                );
                return !hash.getPerfumeId().equals(perfumeId);
            })
            .toList();

        redisTemplate.delete(KEY + userId);

        redisTemplate.opsForList().rightPushAll(KEY + userId, updatedList);
    }

}
