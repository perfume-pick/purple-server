package com.pikachu.purple.application.history.port.in.perfumehistory;

import java.time.Instant;

public interface CreatePerfumeHistoryUseCase {

    void invoke(Command command);

    record Command(
        Long perfumeId,
        Instant searchAt
    ) {}

}
