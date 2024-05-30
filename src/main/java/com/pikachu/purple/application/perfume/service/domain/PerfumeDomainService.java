package com.pikachu.purple.application.perfume.service.domain;

import com.pikachu.purple.domain.perfume.Perfume;
import com.pikachu.purple.domain.perfume.PerfumeBrand;
import java.util.List;

public interface PerfumeDomainService {

    List<Perfume> findByPerfumeBrands(List<PerfumeBrand> brandList);

    List<Perfume> findByUserPreferenceNotes(Long userId);

}
