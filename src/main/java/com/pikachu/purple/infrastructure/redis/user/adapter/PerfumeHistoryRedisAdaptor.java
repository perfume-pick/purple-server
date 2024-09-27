package com.pikachu.purple.infrastructure.redis.user.adapter;

import com.pikachu.purple.application.history.port.out.PerfumeHistoryRepository;
import com.pikachu.purple.domain.history.PerfumeHistory;
import com.pikachu.purple.infrastructure.redis.user.entity.PerfumeHistoryRedisHash;
import com.pikachu.purple.infrastructure.redis.user.repository.PerfumeHistoryRedisRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PerfumeHistoryRedisAdaptor implements PerfumeHistoryRepository {

    private final PerfumeHistoryRedisRepository perfumeHistoryRedisRepository;

    @Override
    public void create(PerfumeHistory perfumeHistory) {
        PerfumeHistoryRedisHash perfumeHistoryRedisHash = PerfumeHistoryRedisHash.toHash(perfumeHistory);

        perfumeHistoryRedisRepository.save(perfumeHistoryRedisHash);
    }

    @Override
    public List<PerfumeHistory> findAllByUserId(Long userId) {
        List<PerfumeHistoryRedisHash> perfumeHistoryRedisHashes = perfumeHistoryRedisRepository.findAllByUserId(userId);

        return perfumeHistoryRedisHashes.stream()
            .map(PerfumeHistoryRedisHash::toDomain)
            .toList();
    }

    @Override
    public void deleteAllPerfumeHistoryByUserId(Long userId) {
        perfumeHistoryRedisRepository.deleteAllPerfumeHistory(userId);
    }

}
