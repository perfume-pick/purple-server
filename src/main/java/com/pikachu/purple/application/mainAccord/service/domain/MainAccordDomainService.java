package com.pikachu.purple.application.mainAccord.service.domain;

import com.pikachu.purple.domain.mainAccord.MainAccord;

import java.util.List;

public interface MainAccordDomainService {

    List<MainAccord> findAllByPerfumeId(
        Long perfumeId,
        int maxSize
    );

}
