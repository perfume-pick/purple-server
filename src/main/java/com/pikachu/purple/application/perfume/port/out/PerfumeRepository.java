package com.pikachu.purple.application.perfume.port.out;

import com.pikachu.purple.domain.perfume.Perfume;
import com.pikachu.purple.domain.user.UserAccord;
import java.util.List;

public interface PerfumeRepository {

    List<Perfume> findAllByUserAccords(List<UserAccord> userAccords);

    /*
    Perfume 반환할 때, PerfumeAccord <value> 값으로 내림차순 정렬
    */
    List<Perfume> findAllByKeyword(String keyword);

    Perfume findById(Long perfumeId);

    List<Perfume> findAllByBrandNames(List<String> brandNames);

}
