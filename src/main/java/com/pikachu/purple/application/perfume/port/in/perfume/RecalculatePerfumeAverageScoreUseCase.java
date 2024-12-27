package com.pikachu.purple.application.perfume.port.in.perfume;

public interface RecalculatePerfumeAverageScoreUseCase {

    void invoke(Command command);

    record Command(Long perfumeId) {}

}
