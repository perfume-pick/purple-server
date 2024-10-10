package com.pikachu.purple.domain.review;

import com.pikachu.purple.domain.user.User;
import java.time.Instant;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Complaint {

    private Long id;
    private String token;
    private Instant reportedAt;

    @Setter
    private Review review;
    @Setter
    private User user;

    public Complaint(
        Long id,
        String token,
        Instant reportedAt
    ) {
        this.id = id;
        this.token = token;
        this.reportedAt = reportedAt;
    }

}
