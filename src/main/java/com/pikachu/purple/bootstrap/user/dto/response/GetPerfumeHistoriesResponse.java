package com.pikachu.purple.bootstrap.user.dto.response;

import com.pikachu.purple.application.history.common.dto.PerfumeHistoryDTO;
import java.util.List;

public record GetPerfumeHistoriesResponse (List<PerfumeHistoryDTO> perfumes) {}
