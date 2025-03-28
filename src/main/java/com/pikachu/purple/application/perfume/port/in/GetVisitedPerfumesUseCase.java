package com.pikachu.purple.application.perfume.port.in;

import com.pikachu.purple.domain.perfume.Perfume;
import java.util.List;

public interface GetVisitedPerfumesUseCase {

    Result findAllByUserIdWithPerfumeAccord(Long userId);

    record Result(List<Perfume> perfumes) {}

}
