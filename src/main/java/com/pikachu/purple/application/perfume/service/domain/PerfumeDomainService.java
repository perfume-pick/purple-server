package com.pikachu.purple.application.perfume.service.domain;

import com.pikachu.purple.domain.perfume.Perfume;
import com.pikachu.purple.domain.user.UserAccord;
import java.util.List;

public interface PerfumeDomainService {

    List<Perfume> findAllByUserAccords(List<UserAccord> userAccords);

    List<Perfume> findAllByKeyword(String keyword);

    Perfume findById(Long perfumeId);

}
