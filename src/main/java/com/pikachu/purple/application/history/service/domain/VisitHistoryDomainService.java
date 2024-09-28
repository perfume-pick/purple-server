package com.pikachu.purple.application.history.service.domain;

import com.pikachu.purple.domain.history.VisitHistory;
import java.time.Instant;
import java.util.List;

public interface VisitHistoryDomainService {

    void create(
        Long userId,
        Long perfumeId,
        Instant searchAt
    );

    List<VisitHistory> findAllByUserId(Long userId);

    void deleteAllVisitHistoryByUserId(Long userId);

}
