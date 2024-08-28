package com.pikachu.purple.infrastructure.persistence.perfume.adaptor;

import com.pikachu.purple.application.perfume.port.out.PerfumeRepository;
import com.pikachu.purple.domain.perfume.Perfume;
import com.pikachu.purple.infrastructure.persistence.perfume.entity.PerfumeJpaEntity;
import com.pikachu.purple.infrastructure.persistence.perfume.repository.PerfumeJpaRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import static com.pikachu.purple.bootstrap.common.exception.BusinessException.PerfumeNotFoundException;

@Component
@RequiredArgsConstructor
public class PerfumeJpaAdaptor implements PerfumeRepository {

    private final static int MAX_SIZE = 30;
    private final PerfumeJpaRepository perfumeJpaRepository;

    public List<Perfume> findByPerfumeBrands(List<String> brands) {
        List<PerfumeJpaEntity> perfumeJpaEntities = perfumeJpaRepository.findByBrandNames(brands);

        return perfumeJpaEntities.stream()
            .map(PerfumeJpaEntity::toDomain)
            .collect(Collectors.toList());
    }

    @Override
    public List<Perfume> findByUserPreferenceNotes(Long userId) {
        PageRequest pageRequest = PageRequest.of(0, MAX_SIZE);
        List<PerfumeJpaEntity> perfumeJpaEntities = perfumeJpaRepository.findByUserPreferenceNotes(
            userId,
            pageRequest
        ).getContent();

        return perfumeJpaEntities.stream()
            .map(PerfumeJpaEntity::toDomain)
            .collect(Collectors.toList());
    }

    @Override
    public List<Perfume> findByKeyword(String keyword) {
        List<PerfumeJpaEntity> perfumes = perfumeJpaRepository.findByKeyword(keyword);

        return perfumes.stream()
            .map(PerfumeJpaEntity::toDomain)
            .toList();
    }

    @Override
    public Perfume findByPerfumeId(Long perfumeId) {
        PerfumeJpaEntity perfumeJpaEntity = perfumeJpaRepository.findByPerfumeId(perfumeId)
                .orElseThrow(() -> PerfumeNotFoundException);

        return PerfumeJpaEntity.toDomain(perfumeJpaEntity);
    }

}
