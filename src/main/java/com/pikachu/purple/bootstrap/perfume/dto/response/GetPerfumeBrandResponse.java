package com.pikachu.purple.bootstrap.perfume.dto.response;

import com.pikachu.purple.domain.perfume.PerfumeBrand;
import java.util.List;

public record GetPerfumeBrandResponse(List<PerfumeBrand> perfumeBrandList) {

}
