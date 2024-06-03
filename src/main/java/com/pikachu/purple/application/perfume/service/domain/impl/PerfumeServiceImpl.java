package com.pikachu.purple.application.perfume.service.domain.impl;

import com.pikachu.purple.application.perfume.port.out.PerfumeRepository;
import com.pikachu.purple.application.perfume.service.domain.PerfumeDomainService;
import com.pikachu.purple.domain.perfume.Perfume;
import com.pikachu.purple.domain.perfume.PerfumeBrand;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PerfumeServiceImpl implements PerfumeDomainService {

    private final PerfumeRepository perfumeRepository;

    @Override
    public List<Perfume> findByPerfumeBrands(List<PerfumeBrand> brandList) {
        return perfumeRepository.findByPerfumeBrands(brandList);
    }

    @Override
    public List<Perfume> findUserPreferenceNotesByUserId(Long userId) {
        return perfumeRepository.findUserPreferenceNotesByUserId(userId);
    }

}
