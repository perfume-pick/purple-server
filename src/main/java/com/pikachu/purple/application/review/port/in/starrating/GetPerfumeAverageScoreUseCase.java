package com.pikachu.purple.application.review.port.in.starrating;

public interface GetPerfumeAverageScoreUseCase {

    Result find(Long perfumeId);

    record Result(double averageScore) {}

}
