package com.pikachu.purple.infrastructure.redis.user.entity;

import com.pikachu.purple.domain.history.PerfumeHistory;
import jakarta.persistence.Id;
import java.time.Instant;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RedisHash(value = "perfume_history")
public class PerfumeHistoryRedisHash {

    @Id
    private Long id;

    private Long perfumeId;

    private Instant searchAt;

    public static PerfumeHistoryRedisHash toHash(PerfumeHistory perfumeHistory) {
        return PerfumeHistoryRedisHash.builder()
            .id(perfumeHistory.getId())
            .perfumeId(perfumeHistory.getPerfumeId())
            .searchAt(perfumeHistory.getSearchAt())
            .build();
    }

    public static PerfumeHistory toDomain(PerfumeHistoryRedisHash hash) {
        return PerfumeHistory.builder()
            .id(hash.getId())
            .perfumeId(hash.getPerfumeId())
            .searchAt(hash.getSearchAt())
            .build();
    }

}
