package com.pikachu.purple.application.mainAccord.service.domain.impl;

import com.pikachu.purple.application.mainAccord.port.out.MainAccordRepository;
import com.pikachu.purple.application.mainAccord.service.domain.MainAccordDomainService;
import com.pikachu.purple.domain.mainAccord.MainAccord;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MainAccordDomainServiceImpl implements MainAccordDomainService {

    private final MainAccordRepository mainAccordRepository;

    @Override
    public List<MainAccord> findAllByPerfumeId(
        Long perfumeId,
        int maxSize
    ) {
        return mainAccordRepository.findAllByPerfumeId(
            perfumeId,
            maxSize
        );
    }

}
