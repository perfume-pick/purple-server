package com.pikachu.purple.infrastructure.redis.user.adapter;

import com.pikachu.purple.application.history.port.out.VisitHistoryRepository;
import com.pikachu.purple.domain.history.VisitHistory;
import com.pikachu.purple.infrastructure.redis.user.entity.VisitHistoryRedisHash;
import com.pikachu.purple.infrastructure.redis.user.repository.VisitHistoryRedisRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class VisitHistoryRedisAdaptor implements VisitHistoryRepository {

    private final VisitHistoryRedisRepository visitHistoryRedisRepository;

    @Override
    public void create(VisitHistory visitHistory) {
        VisitHistoryRedisHash visitHistoryRedisHash = VisitHistoryRedisHash.toHash(
            visitHistory);

        visitHistoryRedisRepository.save(visitHistoryRedisHash);
    }

    @Override
    public List<VisitHistory> findAllByUserId(Long userId) {
        List<VisitHistoryRedisHash> visitHistoryRedisHashes = visitHistoryRedisRepository.findAllByUserId(userId);

        return visitHistoryRedisHashes.stream()
            .map(VisitHistoryRedisHash::toDomain)
            .toList();
    }

    @Override
    public void deleteAllVisitHistoryByUserId(Long userId) {
        visitHistoryRedisRepository.deleteAllVisitHistory(userId);
    }

}
