package com.pikachu.purple.application.perfume.port.in.perfume;

import com.pikachu.purple.domain.perfume.Perfume;

public interface GetPerfumeUseCase {

    Result find(Long perfumeId);

    record Result(Perfume perfume) {}

}
