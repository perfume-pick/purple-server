package com.pikachu.purple.application.useraccrod.service.domain;

import com.pikachu.purple.domain.user.UserAccord;
import java.util.List;

public interface UserAccordDomainService {

    List<UserAccord> findAllByUserId(Long userId);

    void createAll(List<UserAccord> userAccords);

}
