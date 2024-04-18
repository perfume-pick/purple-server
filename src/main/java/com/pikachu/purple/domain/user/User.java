package com.pikachu.purple.domain.user;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    private Long id;
    private String nickName;
    private String email;

    @Builder
    public User(
        Long id,
        String nickName,
        String email
    ){
        this.id = id;
        this.nickName = nickName;
        this.email = email;
    }

}
