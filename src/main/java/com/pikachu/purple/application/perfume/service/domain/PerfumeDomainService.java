package com.pikachu.purple.application.perfume.service.domain;

import com.pikachu.purple.domain.perfume.Perfume;
import java.util.List;

public interface PerfumeDomainService {

    List<Perfume> findByPerfumeBrands(List<String> brands);

    List<Perfume> findByUserPreferenceNotes(Long userId);

}
