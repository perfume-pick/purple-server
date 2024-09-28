package com.pikachu.purple.application.history.port.in.visithistory;

import com.pikachu.purple.application.history.common.dto.VisitHistoryDTO;
import java.util.List;

public interface GetVisitHistoriesUseCase {

    Result invoke();

    record Result(List<VisitHistoryDTO> visitHistoryDTOs) {}

}
