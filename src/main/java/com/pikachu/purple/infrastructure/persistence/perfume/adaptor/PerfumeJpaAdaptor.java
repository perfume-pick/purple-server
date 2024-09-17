package com.pikachu.purple.infrastructure.persistence.perfume.adaptor;

import static com.pikachu.purple.bootstrap.common.exception.BusinessException.PerfumeNotFoundException;

import com.pikachu.purple.application.perfume.port.out.PerfumeRepository;
import com.pikachu.purple.domain.accord.Accord;
import com.pikachu.purple.domain.perfume.Perfume;
import com.pikachu.purple.domain.perfume.PerfumeAccord;
import com.pikachu.purple.domain.user.UserAccord;
import com.pikachu.purple.infrastructure.persistence.accord.AccordJpaEntity;
import com.pikachu.purple.infrastructure.persistence.perfume.entity.PerfumeJpaEntity;
import com.pikachu.purple.infrastructure.persistence.perfume.repository.PerfumeJpaRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PerfumeJpaAdaptor implements PerfumeRepository {

    private final PerfumeJpaRepository perfumeJpaRepository;

    @Override
    public List<Perfume> findAllByBrandNames(List<String> brandNames) {
        List<PerfumeJpaEntity> perfumeJpaEntities = perfumeJpaRepository.findAllByBrandNameIn(
            brandNames);

        return perfumeJpaEntities.stream()
            .map(PerfumeJpaEntity::toDomain)
            .toList();
    }

    @Override
    public List<Perfume> findAllByIds(List<Long> perfumeIds) {
        return List.of();
    }

    @Override
    public List<Perfume> findAllWithPerfumeAccordsByKeyword(String keyword) {
        List<PerfumeJpaEntity> perfumes = perfumeJpaRepository.findByKeyword(keyword);

        return perfumes.stream()
            .map(PerfumeJpaEntity::toDomainWithPerfumeAccord)
            .toList();
    }

    @Override
    public Perfume findById(Long perfumeId) {
        PerfumeJpaEntity perfumeJpaEntity = perfumeJpaRepository.findByPerfumeId(perfumeId)
            .orElseThrow(() -> PerfumeNotFoundException);

        return PerfumeJpaEntity.toDomain(perfumeJpaEntity);
    }

    @Override
    public List<Perfume> findAllWithPerfumeAccordsByAccords(List<Accord> accords) {
        List<AccordJpaEntity> accordJpaEntities = accords.stream()
            .map(AccordJpaEntity::toJpaEntity).toList();

        List<PerfumeJpaEntity> perfumeJpaEntities = perfumeJpaRepository.findAllByAccords(
            accordJpaEntities);

        return perfumeJpaEntities.stream()
            .map(PerfumeJpaEntity::toDomainWithPerfumeAccord)
            .toList();
    }

}
