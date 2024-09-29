package com.pikachu.purple.infrastructure.redis.user.entity;

import com.pikachu.purple.domain.history.VisitHistory;
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
public class VisitHistoryRedisHash {

    @Id
    private Long id;

    private Long perfumeId;

    private Instant searchAt;

    public static VisitHistoryRedisHash toHash(VisitHistory visitHistory) {
        return VisitHistoryRedisHash.builder()
            .id(visitHistory.getId())
            .perfumeId(visitHistory.getPerfumeId())
            .searchAt(visitHistory.getSearchAt())
            .build();
    }

    public static VisitHistory toDomain(VisitHistoryRedisHash hash) {
        return VisitHistory.builder()
            .id(hash.getId())
            .perfumeId(hash.getPerfumeId())
            .searchAt(hash.getSearchAt())
            .build();
    }

}
