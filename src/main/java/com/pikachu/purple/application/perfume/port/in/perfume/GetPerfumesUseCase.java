package com.pikachu.purple.application.perfume.port.in.perfume;

import com.pikachu.purple.domain.perfume.Perfume;
import java.util.List;

public interface GetPerfumesUseCase {

    Result invoke(List<Long> perfumeIds);

    Result invoke(String keyword);

    Result invoke();

    record Result(List<Perfume> perfumes) {}

}
