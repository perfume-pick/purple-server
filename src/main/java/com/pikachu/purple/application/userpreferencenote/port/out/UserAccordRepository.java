package com.pikachu.purple.application.userpreferencenote.port.out;

import com.pikachu.purple.domain.user.UserAccord;
import java.util.List;

public interface UserAccordRepository {

    void save(List<UserAccord> userAccords);

    List<UserAccord> findAllByUserId(Long userId);

}
