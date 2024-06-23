package com.pikachu.purple.application.perfume.port.out;

import com.pikachu.purple.domain.perfume.Perfume;
import java.util.List;

public interface PerfumeRepository {

    List<Perfume> findByPerfumeBrands(List<String> brands);

    List<Perfume> findByUserPreferenceNotes(Long userId);

}
