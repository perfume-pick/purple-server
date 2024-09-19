package com.pikachu.purple.bootstrap.perfume.dto.response;

import com.pikachu.purple.application.perfume.common.dto.BrandPerfumesDTO;
import java.util.List;

public record GetPerfumesByBrandNamesResponse(List<BrandPerfumesDTO> perfumes) {}
