package com.pikachu.purple.application.history.port.out;

import com.pikachu.purple.domain.history.VisitHistory;
import java.util.List;

public interface VisitHistoryRepository {

    void create(VisitHistory visitHistory);

    List<VisitHistory> findAllByUserId(Long userId);

    void deleteAllVisitHistoryByUserId(Long userId);

    void validateNotExist(Long userId, Long perfumeId);

}
