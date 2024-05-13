package com.pikachu.purple.application.perfume.port.in;

import com.pikachu.purple.domain.perfume.Perfume;
import com.pikachu.purple.domain.perfume.PerfumeBrand;
import java.util.List;

public interface PerfumeGetByBrandsUseCase {

    Result invoke(Command command);

    record Command(List<PerfumeBrand> brandList) {
    }
    record Result(List<Perfume> perfumeList) {
    }

}
