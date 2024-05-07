package com.pikachu.purple.application.user.vo;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class NicknameCreator {

    private final String nickName;

    public static NicknameCreator create(int countTotalUsers){
        String createNickname = String.format("퍼픽%03d", countTotalUsers+1);
        return new NicknameCreator(createNickname);
    }
}
