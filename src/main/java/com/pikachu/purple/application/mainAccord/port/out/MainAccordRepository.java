package com.pikachu.purple.application.mainAccord.port.out;

import com.pikachu.purple.domain.mainAccord.MainAccord;

import java.util.List;

public interface MainAccordRepository {

    List<MainAccord> findAllByPerfumeId(Long perfumeId, int maxSize);

}
