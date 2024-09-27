package com.pikachu.purple.infrastructure.redis.user.repository;

import com.pikachu.purple.infrastructure.redis.user.entity.PerfumeHistoryRedisHash;
import java.util.List;

public interface PerfumeHistoryRedisRepository {

    void save(PerfumeHistoryRedisHash perfumeHistoryRedisHash);

    List<PerfumeHistoryRedisHash> findAllByUserId(Long userId);

    void deleteAllPerfumeHistory(Long userId);

}
