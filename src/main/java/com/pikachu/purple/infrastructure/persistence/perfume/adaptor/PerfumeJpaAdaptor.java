package com.pikachu.purple.infrastructure.persistence.perfume.adaptor;

import com.pikachu.purple.application.perfume.port.out.PerfumeRepository;
import com.pikachu.purple.domain.perfume.Perfume;
import com.pikachu.purple.domain.perfume.PerfumeBrand;
import com.pikachu.purple.infrastructure.persistence.perfume.entity.PerfumeJpaEntity;
import com.pikachu.purple.infrastructure.persistence.perfume.repository.PerfumeJpaRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PerfumeJpaAdaptor implements PerfumeRepository {

    private final PerfumeJpaRepository perfumeJpaRepository;

    public List<Perfume> findByPerfumeBrand(List<PerfumeBrand> brandList) {
        List<String> brandNames = brandList.stream()
            .map(PerfumeBrand::getBrandName)
            .toList();

        List<PerfumeJpaEntity> perfumeJpaEntityList = perfumeJpaRepository.findByBrandNameIn(brandNames);

        return perfumeJpaEntityList.stream()
            .map(PerfumeJpaEntity::toDomain)
            .collect(Collectors.toList());
    }

}
