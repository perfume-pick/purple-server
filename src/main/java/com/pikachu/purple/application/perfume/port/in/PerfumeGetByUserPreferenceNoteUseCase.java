package com.pikachu.purple.application.perfume.port.in;

import com.pikachu.purple.domain.perfume.Perfume;
import java.util.List;

public interface PerfumeGetByUserPreferenceNoteUseCase {

    Result invoke();

    record Result(List<Perfume> perfumeList){

    }

}
