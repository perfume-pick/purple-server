package com.pikachu.purple.infrastructure.persistence.perfume.adaptor;

import com.pikachu.purple.application.perfume.port.out.PerfumeRepository;
import com.pikachu.purple.domain.perfume.Perfume;
import com.pikachu.purple.domain.perfume.PerfumeBrand;
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

    private final static int ZERO = 0;
    private final static int THIRTY = 30;
    private final PerfumeJpaRepository perfumeJpaRepository;

    public List<Perfume> findByPerfumeBrands(List<PerfumeBrand> brandList) {
        List<String> brandNames = brandList.stream()
            .map(PerfumeBrand::getBrandName)
            .toList();

        List<PerfumeJpaEntity> perfumeJpaEntityList = perfumeJpaRepository.findByBrandNameIn(brandNames);

        return perfumeJpaEntityList.stream()
            .map(PerfumeJpaEntity::toDomain)
            .collect(Collectors.toList());
    }

    @Override
    public List<Perfume> findUserPreferenceNotesByUserId(Long userId) {
        PageRequest pageRequest = PageRequest.of(ZERO, THIRTY);
        List<PerfumeJpaEntity> perfumeJpaEntityList = perfumeJpaRepository.findUserPreferenceNotesByUserId(
            userId,
            pageRequest
        ).getContent();

        return perfumeJpaEntityList.stream()
            .map(PerfumeJpaEntity::toDomain)
            .collect(Collectors.toList());
    }

}
