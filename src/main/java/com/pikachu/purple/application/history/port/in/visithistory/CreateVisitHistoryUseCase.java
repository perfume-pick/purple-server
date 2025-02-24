package com.pikachu.purple.application.history.port.in.visithistory;

import java.time.Instant;

public interface CreateVisitHistoryUseCase {

    void create(
        Long userId,
        Long perfumeId,
        Instant searchAt
    );

}
