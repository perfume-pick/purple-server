package com.pikachu.purple.application.perfume.port.in;

import com.pikachu.purple.domain.perfume.Perfume;
import java.util.List;

public interface PerfumeGetByKeywordUseCase {

    Result invoke(Command command);

    record Command(String keyword){

    }

    record Result(List<Perfume> perfumes){

    }

}
