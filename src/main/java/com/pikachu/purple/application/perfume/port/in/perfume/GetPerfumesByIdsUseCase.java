package com.pikachu.purple.application.perfume.port.in.perfume;

import com.pikachu.purple.domain.perfume.Perfume;
import java.util.List;

public interface GetPerfumesByIdsUseCase {

    Result invoke(Command command);

    record Command(List<Long> perfumeIds) {}

    record Result(List<Perfume> perfumes) {}

}
