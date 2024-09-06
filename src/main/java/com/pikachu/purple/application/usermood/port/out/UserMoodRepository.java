package com.pikachu.purple.application.usermood.port.out;

import com.pikachu.purple.domain.user.entity.UserMood;
import java.util.List;

public interface UserMoodRepository {

    void create(List<UserMood> userMoods);

}
