package com.pikachu.purple.bootstrap.user.dto.response;

import com.pikachu.purple.application.history.common.dto.VisitHistoryDTO;
import java.util.List;

public record GetVisitHistoriesResponse(List<VisitHistoryDTO> perfumes) {}
