package com.pikachu.purple.application.usermood.service.domain;

import java.util.List;

public interface UserMoodDomainService {

    void create(
        List<Long> userMoodIds,
        Long userId,
        Long perfumeId,
        List<String> moods
    );

}
