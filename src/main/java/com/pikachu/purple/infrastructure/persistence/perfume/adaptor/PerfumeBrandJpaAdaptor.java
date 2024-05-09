package com.pikachu.purple.infrastructure.persistence.perfume.adaptor;

import com.pikachu.purple.application.perfume.port.out.PerfumeBrandRepository;
import com.pikachu.purple.domain.perfume.PerfumeBrand;
import com.pikachu.purple.infrastructure.persistence.perfume.entity.PerfumeBrandJpaEntity;
import com.pikachu.purple.infrastructure.persistence.perfume.repository.PerfumeBrandJpaRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PerfumeBrandJpaAdaptor implements PerfumeBrandRepository {

    private final PerfumeBrandJpaRepository perfumeBrandJpaRepository;

    @Override
    public List<PerfumeBrand> findAll() {
        List<PerfumeBrandJpaEntity> perfumeBrandEntityList = perfumeBrandJpaRepository.findAll();
        return perfumeBrandEntityList.stream()
            .map(PerfumeBrandJpaEntity::toDomain)
            .collect(Collectors.toList());
    }

}
