package com.pikachu.purple.infrastructure.persistence.perfume.adaptor;

import com.pikachu.purple.application.perfume.port.out.PerfumeAccordRepository;
import com.pikachu.purple.domain.perfume.PerfumeAccord;
import com.pikachu.purple.infrastructure.persistence.perfume.entity.PerfumeAccordJpaEntity;
import com.pikachu.purple.infrastructure.persistence.perfume.repository.PerfumeAccordJpaRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Limit;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PerfumeAccordJpaAdaptor implements PerfumeAccordRepository {

    private final PerfumeAccordJpaRepository perfumeAccordJpaRepository;

    @Override
    public List<PerfumeAccord> findAllByPerfumeId(Long perfumeId, int maxSize) {
        List<PerfumeAccordJpaEntity> perfumeAccordJpaEntities = perfumeAccordJpaRepository
            .findAllByPerfumeId(perfumeId, Limit.of(maxSize));

        return perfumeAccordJpaEntities.stream()
            .map(PerfumeAccordJpaEntity::toDomain)
            .toList();
    }

}
