package com.pikachu.purple.bootstrap.perfume.dto.request;

import com.pikachu.purple.domain.perfume.PerfumeBrand;
import java.util.List;

public record GetPerfumeRequest(List<PerfumeBrand> brandList) {

}
