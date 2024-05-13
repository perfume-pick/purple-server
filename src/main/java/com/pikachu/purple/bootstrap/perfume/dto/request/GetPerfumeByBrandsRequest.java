package com.pikachu.purple.bootstrap.perfume.dto.request;

import com.pikachu.purple.domain.perfume.PerfumeBrand;
import java.util.List;

public record GetPerfumeByBrandsRequest(List<PerfumeBrand> brandList) {
}
