package com.pikachu.purple.application.history.port.out;

import com.pikachu.purple.domain.history.PerfumeHistory;
import java.util.List;

public interface PerfumeHistoryRepository {

    void create(PerfumeHistory perfumeHistory);

    List<PerfumeHistory> findAllByUserId(Long userId);

    void deleteAllPerfumeHistoryByUserId(Long userId);

}
