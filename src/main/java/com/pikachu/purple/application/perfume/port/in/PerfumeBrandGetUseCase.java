package com.pikachu.purple.application.perfume.port.in;

import com.pikachu.purple.domain.perfume.PerfumeBrand;
import java.util.List;

public interface PerfumeBrandGetUseCase {

    Result invoke();

    record Result(List<PerfumeBrand> perfumeBrandList) {
    }

}
