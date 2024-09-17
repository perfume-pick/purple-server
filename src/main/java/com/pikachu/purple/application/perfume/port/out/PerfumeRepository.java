package com.pikachu.purple.application.perfume.port.out;

import com.pikachu.purple.domain.accord.Accord;
import com.pikachu.purple.domain.perfume.Perfume;
import com.pikachu.purple.domain.perfume.PerfumeAccord;
import com.pikachu.purple.domain.user.UserAccord;
import java.util.List;

public interface PerfumeRepository {

    /*
    Perfume 반환할 때, PerfumeAccord <value> 값으로 내림차순 정렬
    */
    List<Perfume> findAllWithPerfumeAccordsByKeyword(String keyword);

    Perfume findById(Long perfumeId);

    List<Perfume> findAllByBrandNames(List<String> brandNames);

    /*
    잘 반환해주세요~
     */
    List<Perfume> findAllByIds(List<Long> perfumeIds);

    List<Perfume> findAllWithPerfumeAccordsByAccords(List<Accord> accords);

}
