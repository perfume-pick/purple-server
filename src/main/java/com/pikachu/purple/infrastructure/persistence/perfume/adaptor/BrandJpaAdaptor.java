package com.pikachu.purple.infrastructure.persistence.perfume.adaptor;

import com.pikachu.purple.application.perfume.port.out.BrandRepository;
import com.pikachu.purple.domain.perfume.Brand;
import com.pikachu.purple.infrastructure.persistence.perfume.entity.BrandJpaEntity;
import com.pikachu.purple.infrastructure.persistence.perfume.repository.BrandJpaRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BrandJpaAdaptor implements BrandRepository {

    private final BrandJpaRepository brandJpaRepository;

    @Override
    public List<Brand> findAll() {
        List<BrandJpaEntity> perfumeBrandEntityList = brandJpaRepository.findAll();
        return perfumeBrandEntityList.stream()
            .map(BrandJpaEntity::toDomain)
            .toList();
    }

    @Override
    public List<Brand> findAllWithPerfumes(List<String> brandNames) {
        List<BrandJpaEntity> brandJpaEntities = brandJpaRepository.findByNameIn(brandNames);

        return brandJpaEntities.stream()
            .map(BrandJpaEntity::toDomainWithPerfume)
            .toList();
    }

}
