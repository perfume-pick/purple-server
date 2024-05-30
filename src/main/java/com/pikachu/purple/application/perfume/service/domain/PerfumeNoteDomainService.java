package com.pikachu.purple.application.perfume.service.domain;

import com.pikachu.purple.domain.perfume.PerfumeNote;
import java.util.List;

public interface PerfumeNoteDomainService {

    List<PerfumeNote> getByPerfumeIdList(List<Long> perfumeId);

}
