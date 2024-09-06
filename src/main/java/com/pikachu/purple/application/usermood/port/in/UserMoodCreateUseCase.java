package com.pikachu.purple.application.usermood.port.in;

import java.util.List;

public interface UserMoodCreateUseCase {

    void invoke(Command command);

    record Command(
        Long perfumeId,
        List<String> moods
    ){

    }

}
