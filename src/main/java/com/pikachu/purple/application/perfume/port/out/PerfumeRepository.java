package com.pikachu.purple.application.perfume.port.out;

import com.pikachu.purple.domain.perfume.Perfume;
import com.pikachu.purple.domain.perfume.PerfumeBrand;
import java.util.List;

public interface PerfumeRepository {

    List<Perfume> findByPerfumeBrands(List<PerfumeBrand> brandList);

}
