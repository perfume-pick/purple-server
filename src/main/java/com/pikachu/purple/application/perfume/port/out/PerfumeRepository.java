package com.pikachu.purple.application.perfume.port.out;

import com.pikachu.purple.domain.accord.Accord;
import com.pikachu.purple.domain.perfume.Brand;
import com.pikachu.purple.domain.perfume.Perfume;
import java.util.List;

public interface PerfumeRepository {

    Perfume findByPerfumeId(Long perfumeId);

    List<Perfume> findAll(Brand brand);

    List<Perfume> findAll(String keyword);

    List<Perfume> findAll(List<Long> perfumeIds);

    List<Perfume> findAll(List<Accord> accords, int maxSize);

    List<Perfume> findAllHavingReviewCountNotZeroOrderByReviewCount(int maxSize);

    List<Long> findAllId();

    void updateAverageScore(Long perfumeId, double averageScore);

    void updateAllAverageScore(List<Perfume> perfumes);

}
