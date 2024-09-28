package com.pikachu.purple.infrastructure.redis.user.repository;

import com.pikachu.purple.infrastructure.redis.user.entity.VisitHistoryRedisHash;
import java.util.List;

public interface VisitHistoryRedisRepository {

    void save(VisitHistoryRedisHash visitHistoryRedisHash);

    List<VisitHistoryRedisHash> findAllByUserId(Long userId);

    void deleteAllVisitHistory(Long userId);

}
