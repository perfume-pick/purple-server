package com.pikachu.purple.bootstrap.perfume.dto.response;

import com.pikachu.purple.domain.perfume.Perfume;
import java.util.List;

public record GetPerfumeByBrandsResponse(List<Perfume> perfumeList) {
}
