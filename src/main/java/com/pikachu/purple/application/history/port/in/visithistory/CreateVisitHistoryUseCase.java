package com.pikachu.purple.application.history.port.in.visithistory;

import java.time.Instant;

public interface CreateVisitHistoryUseCase {

    void invoke(
        Long perfumeId,
        Instant searchAt
    );

}
