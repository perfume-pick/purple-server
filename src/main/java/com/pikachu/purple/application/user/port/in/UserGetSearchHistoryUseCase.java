package com.pikachu.purple.application.user.port.in;

import com.pikachu.purple.domain.user.entity.UserSearchHistory;
import java.util.List;

public interface UserGetSearchHistoryUseCase {

    Result invoke();

    record Result(List<UserSearchHistory> userSearchHistories){

    }

}
