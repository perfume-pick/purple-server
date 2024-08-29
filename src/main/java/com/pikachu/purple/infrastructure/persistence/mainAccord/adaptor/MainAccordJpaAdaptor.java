package com.pikachu.purple.infrastructure.persistence.mainAccord.adaptor;

import com.pikachu.purple.application.mainAccord.port.out.MainAccordRepository;
import com.pikachu.purple.domain.mainAccord.MainAccord;
import com.pikachu.purple.infrastructure.persistence.mainAccord.entity.MainAccordJpaEntity;
import com.pikachu.purple.infrastructure.persistence.mainAccord.repository.MainAccordJpaRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Limit;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MainAccordJpaAdaptor implements MainAccordRepository {

    private final MainAccordJpaRepository mainAccordJpaRepository;

    @Override
    public List<MainAccord> findAllByPerfumeId(Long perfumeId, int maxSize) {
        List<MainAccordJpaEntity> mainAccordJpaEntities = mainAccordJpaRepository
            .findAllByPerfumeId(perfumeId, Limit.of(maxSize));

        return mainAccordJpaEntities.stream()
            .map(MainAccordJpaEntity::toDomain)
            .collect(Collectors.toList());
    }

}
