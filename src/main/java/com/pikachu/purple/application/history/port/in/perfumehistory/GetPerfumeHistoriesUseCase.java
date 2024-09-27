package com.pikachu.purple.application.history.port.in.perfumehistory;

import com.pikachu.purple.application.history.common.dto.PerfumeHistoryDTO;
import java.util.List;

public interface GetPerfumeHistoriesUseCase {

    Result invoke();

    record Result(List<PerfumeHistoryDTO> perfumeHistoryDTOs) {}

}
