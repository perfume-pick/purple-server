package com.pikachu.purple.application.perfume.port.out;

import com.pikachu.purple.domain.perfume.Perfume;
import java.util.List;

public interface PerfumeDocumentRepository {

    List<Perfume> findByKeyword(String keyword);

    void create(Perfume perfume);

}
