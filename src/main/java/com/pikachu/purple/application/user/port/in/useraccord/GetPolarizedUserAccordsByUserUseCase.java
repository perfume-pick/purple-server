package com.pikachu.purple.application.user.port.in.useraccord;

import com.pikachu.purple.application.user.common.dto.PolarizedUserAccordDTO;

public interface GetPolarizedUserAccordsByUserUseCase {

    Result invoke();

    record Result(PolarizedUserAccordDTO polarizedUserAccordDTO) {}

}
