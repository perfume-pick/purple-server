package com.pikachu.purple.bootstrap.user.dto.response;

import com.pikachu.purple.domain.user.entity.UserCurrentSearchLog;
import java.util.List;

public record SearchPageResponse(List<UserCurrentSearchLog> userCurrentSearchLogs) {

}
