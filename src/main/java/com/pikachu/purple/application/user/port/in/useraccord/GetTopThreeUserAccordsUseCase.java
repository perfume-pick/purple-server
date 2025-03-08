package com.pikachu.purple.application.user.port.in.useraccord;

import com.pikachu.purple.domain.user.UserAccord;
import java.util.List;

public interface GetTopThreeUserAccordsUseCase {

    Result findByUserId(Long userId);

    record Result(List<UserAccord> userAccords){}

}
