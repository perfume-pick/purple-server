package com.pikachu.purple.application.perfume.service.domain;

import com.pikachu.purple.domain.perfume.PerfumeNote;
import java.util.List;

public interface PerfumeNoteDomainService {

    List<PerfumeNote> getAllByPerfumeIds(List<Long> perfumeId);

}
