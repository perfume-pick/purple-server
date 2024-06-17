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

@Component
@RequiredArgsConstructor
public class PerfumeJpaAdaptor implements PerfumeRepository {

    private final static int MAX_SIZE = 30;
    private final PerfumeJpaRepository perfumeJpaRepository;

    public List<Perfume> findByPerfumeBrands(List<String> brandList) {
        List<PerfumeJpaEntity> perfumeJpaEntityList = perfumeJpaRepository.findByBrandNames(brandList);

        return perfumeJpaEntityList.stream()
            .map(PerfumeJpaEntity::toDomain)
            .collect(Collectors.toList());
    }

    @Override
    public List<Perfume> findByUserPreferenceNotes(Long userId) {
        PageRequest pageRequest = PageRequest.of(0, MAX_SIZE);
        List<PerfumeJpaEntity> perfumeJpaEntityList = perfumeJpaRepository.findByUserPreferenceNotes(
            userId,
            pageRequest
        ).getContent();

        return perfumeJpaEntityList.stream()
            .map(PerfumeJpaEntity::toDomain)
            .collect(Collectors.toList());
    }

}
