package com.pikachu.purple.application.useraccrod.port.out;

import com.pikachu.purple.domain.user.UserAccord;
import java.util.List;

public interface UserAccordRepository {

    void createAll(List<UserAccord> userAccords);

    List<UserAccord> findAllByUserId(Long userId);

}
