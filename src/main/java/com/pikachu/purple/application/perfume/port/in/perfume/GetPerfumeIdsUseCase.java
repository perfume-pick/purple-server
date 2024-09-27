package com.pikachu.purple.application.perfume.port.in.perfume;

import java.util.List;

public interface GetPerfumeIdsUseCase {

    Result invoke();

    record Result(List<Long> perfumeIds) {}

}
