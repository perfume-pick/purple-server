package com.pikachu.purple.application.history.port.in.visithistory;

import com.pikachu.purple.domain.history.VisitHistory;
import java.util.List;

public interface GetVisitHistoriesUseCase {

    Result findAllUserId(Long userId);

    record Result(List<VisitHistory> visitHistories) {}

}
