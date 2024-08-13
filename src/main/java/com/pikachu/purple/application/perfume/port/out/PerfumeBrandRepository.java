package com.pikachu.purple.application.perfume.port.out;

import com.pikachu.purple.domain.perfume.PerfumeBrand;
import java.util.List;

public interface PerfumeBrandRepository {

    List<PerfumeBrand> getTopThirty();

}
