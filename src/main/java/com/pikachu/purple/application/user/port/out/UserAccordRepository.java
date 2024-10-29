package com.pikachu.purple.application.user.port.out;

import com.pikachu.purple.domain.user.UserAccord;
import java.util.List;

public interface UserAccordRepository {

    void createAll(Long userId, List<UserAccord> userAccords);

    List<UserAccord> findAllOrderByScoreDesc(Long userId, int maxSize);

    List<UserAccord> findAllOrderByScoreAsc(Long userId, int maxSize);

}
