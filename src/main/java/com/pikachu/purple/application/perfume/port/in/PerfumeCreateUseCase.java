package com.pikachu.purple.application.perfume.port.in;

public interface PerfumeCreateUseCase {

    void invoke(Command command);

    record Command(
        Long perfumeId,
        String perfumeName,
        String brandName
    ) {

    }

}
