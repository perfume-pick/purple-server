package com.pikachu.purple.application.perfume.port.in;

import com.pikachu.purple.domain.perfume.Perfume;
import java.util.List;

public interface PerfumeGetByBrandsUseCase {

    Result invoke(Command command);

    record Command(List<String> brands) {

    }

    record Result(List<Perfume> perfumes) {

    }

}
