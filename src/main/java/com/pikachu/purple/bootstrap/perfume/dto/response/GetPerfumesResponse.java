package com.pikachu.purple.bootstrap.perfume.dto.response;

import com.pikachu.purple.application.perfume.common.dto.PerfumeDTO;
import java.util.List;

public record GetPerfumesResponse(List<PerfumeDTO> perfumes) {}
