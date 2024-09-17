package com.pikachu.purple.application.perfume.port.in;

import com.pikachu.purple.domain.perfume.Perfume;

public interface GetPerfumeByIdUseCase {

    Result invoke(Command command);

    record Command(Long perfumeId) {}

    record Result(Perfume perfume) {}

}
