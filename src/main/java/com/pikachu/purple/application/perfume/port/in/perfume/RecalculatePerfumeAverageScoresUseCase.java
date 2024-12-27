package com.pikachu.purple.application.perfume.port.in.perfume;

import java.util.List;

public interface RecalculatePerfumeAverageScoresUseCase {

    void invoke();

    void invoke(Command command);

    record Command(List<Long> perfumeIds) {};
}
