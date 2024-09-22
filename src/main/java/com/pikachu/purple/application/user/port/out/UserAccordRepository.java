package com.pikachu.purple.application.user.port.out;

import com.pikachu.purple.domain.user.UserAccord;
import java.util.List;

public interface UserAccordRepository {

    void createAll(Long userId, List<UserAccord> userAccords);

    List<UserAccord> findAllByUserId(Long userId);

}
