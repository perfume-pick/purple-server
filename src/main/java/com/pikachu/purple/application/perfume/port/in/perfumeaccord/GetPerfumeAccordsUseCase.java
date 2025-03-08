package com.pikachu.purple.application.perfume.port.in.perfumeaccord;

import com.pikachu.purple.domain.perfume.Perfume;
import com.pikachu.purple.domain.perfume.PerfumeAccord;
import java.util.List;

public interface GetPerfumeAccordsUseCase {

    Result findAll(Perfume perfume);

    record Result(List<PerfumeAccord> perfumeAccords) {}

}
