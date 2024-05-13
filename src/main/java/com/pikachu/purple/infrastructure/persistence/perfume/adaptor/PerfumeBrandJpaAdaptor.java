package com.pikachu.purple.infrastructure.persistence.perfume.adaptor;

import com.pikachu.purple.application.perfume.port.out.PerfumeBrandRepository;
import com.pikachu.purple.domain.perfume.PerfumeBrand;
import com.pikachu.purple.infrastructure.persistence.perfume.entity.PerfumeBrandJpaEntity;
import com.pikachu.purple.infrastructure.persistence.perfume.repository.PerfumeBrandJpaRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PerfumeBrandJpaAdaptor implements PerfumeBrandRepository {

    private final static int ZERO = 0;
    private final static int THIRTY = 30;
    private final PerfumeBrandJpaRepository perfumeBrandJpaRepository;

    @Override
    public List<PerfumeBrand> findTopThirtyBrands() {
        Pageable pageable = PageRequest.of(ZERO , THIRTY);
        Page<PerfumeBrandJpaEntity> perfumeBrandEntityList = perfumeBrandJpaRepository.findAll(pageable);
        return perfumeBrandEntityList.stream()
            .map(PerfumeBrandJpaEntity::toDomain)
            .collect(Collectors.toList());
    }

}
