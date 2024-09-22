package com.pikachu.purple.bootstrap.perfume.dto.response;

import com.pikachu.purple.application.perfume.common.dto.BrandDTO;
import java.util.List;

public record GetBrandsResponse(List<BrandDTO> brands) {}
