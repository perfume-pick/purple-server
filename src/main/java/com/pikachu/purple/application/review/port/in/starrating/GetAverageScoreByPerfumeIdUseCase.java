package com.pikachu.purple.application.review.port.in.starrating;

public interface GetAverageScoreByPerfumeIdUseCase {

    Result invoke(Command command);

    record Command(Long perfumeId) {}

    record Result(double averageScore) {}

}
