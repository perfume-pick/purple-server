package com.pikachu.purple.application.history.service.domain;

import com.pikachu.purple.domain.history.PerfumeHistory;
import java.time.Instant;
import java.util.List;

public interface PerfumeHistoryDomainService {

    void create(
        Long userId,
        Long perfumeId,
        Instant searchAt
    );

    List<PerfumeHistory> findAllByUserId(Long userId);

    void deleteAllPerfumeHistoryByUserId(Long userId);

}
