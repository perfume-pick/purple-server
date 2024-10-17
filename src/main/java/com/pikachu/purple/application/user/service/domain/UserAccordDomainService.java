package com.pikachu.purple.application.user.service.domain;

import com.pikachu.purple.domain.user.UserAccord;
import java.util.List;

public interface UserAccordDomainService {

    List<UserAccord> findAllOrderByScoreDesc(
        Long userId,
        int maxSize
    );

    void createAll(Long userId, List<UserAccord> userAccords);

}
