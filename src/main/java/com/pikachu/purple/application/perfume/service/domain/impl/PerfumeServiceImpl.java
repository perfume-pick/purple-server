package com.pikachu.purple.application.perfume.service.domain.impl;

import com.pikachu.purple.application.perfume.port.out.PerfumeRepository;
import com.pikachu.purple.application.perfume.service.domain.PerfumeDomainService;
import com.pikachu.purple.domain.perfume.Perfume;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PerfumeServiceImpl implements PerfumeDomainService {

    private final PerfumeRepository perfumeRepository;

    @Override
    public List<Perfume> findByPerfumeBrands(List<String> brands) {
        return perfumeRepository.findByPerfumeBrands(brands);
    }

    @Override
    public List<Perfume> findByUserPreferenceNotes(Long userId) {
        return perfumeRepository.findByUserPreferenceNotes(userId);
    }

}
