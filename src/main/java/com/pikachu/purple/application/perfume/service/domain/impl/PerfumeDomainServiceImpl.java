package com.pikachu.purple.application.perfume.service.domain.impl;

import com.pikachu.purple.application.perfume.port.out.PerfumeRepository;
import com.pikachu.purple.application.perfume.service.domain.PerfumeDomainService;
import com.pikachu.purple.domain.perfume.Perfume;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PerfumeDomainServiceImpl implements PerfumeDomainService {

    private final PerfumeRepository perfumeRepository;

    @Override
    public List<Perfume> findAllByPerfumeBrands(List<String> brands) {
        return perfumeRepository.findAllByPerfumeBrands(brands);
    }

    @Override
    public List<Perfume> findByUserPreferenceNotes(Long userId) {
        return perfumeRepository.findByUserPreferenceNotes(userId);
    }

    @Override
    public List<Perfume> findByKeyword(String keyword) {
        return perfumeRepository.findByKeyword("%" + keyword + "%");
    }

    @Override
    public Perfume findByPerfumeId(Long perfumeId) {
        return perfumeRepository.findByPerfumeId(perfumeId);
    }

}
