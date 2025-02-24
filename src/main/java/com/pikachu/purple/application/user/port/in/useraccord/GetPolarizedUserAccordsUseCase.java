package com.pikachu.purple.application.user.port.in.useraccord;

import com.pikachu.purple.application.user.common.dto.PolarizedUserAccordDTO;

public interface GetPolarizedUserAccordsUseCase {

    Result find(Long userId);

    record Result(PolarizedUserAccordDTO polarizedUserAccordDTO) {}

}
