package com.pikachu.purple.application.perfume.port.in.perfume;

import com.pikachu.purple.application.perfume.common.dto.PerfumeDTO;
import java.util.List;

public interface GetPerfumesByKeywordUseCase {

    Result invoke(Command command);

    record Command(String keyword){}

    record Result(List<PerfumeDTO> perfumeDTOs){}

}
