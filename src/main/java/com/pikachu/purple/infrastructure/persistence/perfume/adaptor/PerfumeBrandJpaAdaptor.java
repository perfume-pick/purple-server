package com.pikachu.purple.infrastructure.persistence.perfume.adaptor;

import com.pikachu.purple.application.perfume.port.out.PerfumeBrandRepository;
import com.pikachu.purple.domain.perfume.PerfumeBrand;
import com.pikachu.purple.infrastructure.persistence.perfume.entity.BrandJpaEntity;
import com.pikachu.purple.infrastructure.persistence.perfume.repository.PerfumeBrandJpaRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PerfumeBrandJpaAdaptor implements PerfumeBrandRepository {

    private static final int MAX_SIZE = 30;
    private final PerfumeBrandJpaRepository perfumeBrandJpaRepository;

    @Override
    public List<PerfumeBrand> getTopThirty() {
        List<BrandJpaEntity> perfumeBrandEntityList = perfumeBrandJpaRepository.getTopThirtyBy(MAX_SIZE);
        return perfumeBrandEntityList.stream()
            .map(BrandJpaEntity::toDomain)
            .toList();
    }

}
