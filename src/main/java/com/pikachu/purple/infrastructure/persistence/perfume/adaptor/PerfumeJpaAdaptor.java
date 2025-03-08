package com.pikachu.purple.infrastructure.persistence.perfume.adaptor;

import static com.pikachu.purple.bootstrap.common.exception.BusinessException.PerfumeNotFoundException;

import com.pikachu.purple.application.perfume.port.out.PerfumeRepository;
import com.pikachu.purple.domain.accord.Accord;
import com.pikachu.purple.domain.perfume.Brand;
import com.pikachu.purple.domain.perfume.Perfume;
import com.pikachu.purple.infrastructure.persistence.accord.entity.AccordJpaEntity;
import com.pikachu.purple.infrastructure.persistence.perfume.entity.PerfumeJpaEntity;
import com.pikachu.purple.infrastructure.persistence.perfume.repository.PerfumeJpaRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Limit;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class PerfumeJpaAdaptor implements PerfumeRepository {

    private final PerfumeJpaRepository perfumeJpaRepository;

    @Override
    public Perfume findByPerfumeId(Long perfumeId) {
        PerfumeJpaEntity perfumeJpaEntity = perfumeJpaRepository.findById(perfumeId)
            .orElseThrow(() -> PerfumeNotFoundException);

        return PerfumeJpaEntity.toDomain(perfumeJpaEntity);
    }

    @Override
    public List<Perfume> findAll(Brand brand) {
        List<PerfumeJpaEntity> perfumeJpaEntities = perfumeJpaRepository.findAllByBrandName(
            brand.getName()
        );

        return perfumeJpaEntities.stream()
            .map(PerfumeJpaEntity::toDomain)
            .toList();
    }

    @Override
    public List<Perfume> findAll(String keyword) {
        List<PerfumeJpaEntity> perfumes = perfumeJpaRepository.findAllByKeyword(keyword);

        return perfumes.stream()
            .map(PerfumeJpaEntity::toDomain)
            .toList();
    }

    @Override
    public List<Perfume> findAll(List<Long> perfumeIds) {
        List<PerfumeJpaEntity> perfumeJpaEntities = perfumeJpaRepository.findAllByIdIn(perfumeIds);

        return perfumeJpaEntities.stream()
            .map(PerfumeJpaEntity::toDomain)
            .toList();
    }

    @Override
    public List<Perfume> findAll(List<Accord> accords, int maxSize) {
        List<AccordJpaEntity> accordJpaEntities = accords.stream()
            .map(AccordJpaEntity::toJpaEntity).toList();

        List<PerfumeJpaEntity> perfumeJpaEntities = perfumeJpaRepository.findAllByAccords(
            accordJpaEntities,
            Limit.of(maxSize)
        );

        return perfumeJpaEntities.stream()
            .map(PerfumeJpaEntity::toDomain)
            .toList();
    }

    @Override
    public List<Perfume> findAllHavingReviewCountNotZeroOrderByReviewCount(int maxSize) {
        List<PerfumeJpaEntity> perfumeJpaEntities = perfumeJpaRepository.findAllHavingReviewCountNotZeroOrderByReviewCount(
            Limit.of(maxSize));

        return perfumeJpaEntities.stream()
            .map(PerfumeJpaEntity::toDomain)
            .toList();
    }

    @Override
    public List<Long> findAllId() {
        return perfumeJpaRepository.findAllId();
    }

    @Override
    public void updateAverageScore(
        Long perfumeId,
        double averageScore
    ) {
        perfumeJpaRepository.updateAverageScoreByPerfumeId(
            perfumeId,
            averageScore
        );
    }

    @Override
    public void updateAllAverageScore(List<Perfume> perfumes) {
        for (Perfume perfume : perfumes) {
            perfumeJpaRepository.updateAverageScoreByPerfumeId(
                perfume.getId(),
                perfume.getAverageScore()
            );
        }
    }

}
