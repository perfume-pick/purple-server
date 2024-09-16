package com.pikachu.purple.application.userpreferencenote.port.in;

import com.pikachu.purple.domain.user.UserAccord;
import java.util.List;

public interface GetUserAccordsUseCase {

    Result invoke();

    record Result(List<UserAccord> userAccords){}

}
