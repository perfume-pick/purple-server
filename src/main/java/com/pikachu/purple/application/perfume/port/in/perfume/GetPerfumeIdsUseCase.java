package com.pikachu.purple.application.perfume.port.in.perfume;

import com.pikachu.purple.domain.perfume.Perfume;
import java.util.List;

public interface GetPerfumeIdsUseCase {

    Result invoke();

    record Result(List<Long> perfumeIds) {}

}
