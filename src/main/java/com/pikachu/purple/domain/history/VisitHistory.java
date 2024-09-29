package com.pikachu.purple.domain.history;

import java.time.Instant;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class VisitHistory {

    Long id;
    Long perfumeId;
    Instant searchAt;

}
