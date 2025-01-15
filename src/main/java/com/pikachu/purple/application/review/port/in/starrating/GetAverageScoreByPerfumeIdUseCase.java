package com.pikachu.purple.application.review.port.in.starrating;

public interface GetAverageScoreByPerfumeIdUseCase {

    Result invoke(Long perfumeId);

    record Result(double averageScore) {}

}
