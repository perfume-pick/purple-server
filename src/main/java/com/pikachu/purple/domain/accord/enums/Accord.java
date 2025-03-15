package com.pikachu.purple.domain.accord.enums;

import lombok.Getter;

@Getter
public enum Accord {
    ALDEHYDIC("알데히드 향"),
    ALMOND("아몬드"),
    AMBER("앰버"),
    ANIMALIC("애니멀릭"),
    ANIS("아니스"),
    AQUATIC("아쿠아틱"),
    AROMATIC("아로마틱"),
    BALSAMIC("발삼"),
    CACAO("카카오"),
    CAMPHOR("캄포"),
    CARAMEL("카라멜"),
    CHAMPAGNE("샴페인"),
    CHERRY("체리"),
    CINNAMON("시나몬"),
    CITRUS("시트러스"),
    COCONUT("코코넛"),
    COFFEE("커피"),
    CONIFER("코니퍼"),
    EARTHY("흙내음"),
    FLORAL("플로럴"),
    FRESH("프레시"),
    FRESH_SPICY("프레시 스파이시"),
    FRUITY("프루티"),
    GREEN("그린"),
    HERBAL("허벌"),
    HONEY("꿀"),
    IRIS("아이리스"),
    LACTONIC("락토닉"),
    LAVENDER("라벤더"),
    LEATHER("레더"),
    MARINE("마린"),
    METALLIC("메탈릭"),
    MINERAL("미네랄"),
    MOSSY("모스"),
    MUSKY("머스크"),
    NUTTY("넛티"),
    OUD("우드"),
    OZONIC("오존틱"),
    PATCHOULI("파출리"),
    POWDERY("파우더리"),
    ROSE("로즈"),
    RUM("럼"),
    SALTY("솔티"),
    SAVORY("세이보리"),
    SMOKY("스모키"),
    SOAPY("소피"),
    SOFT_SPICY("소프트 스파이시"),
    SOUR("산미"),
    SWEET("스위트"),
    TOBACCO("타바코"),
    TROPICAL("트로피컬"),
    TUBEROSE("튜베로즈"),
    VANILLA("바닐라"),
    VIOLET("바이올렛"),
    WARM_SPICY("웜 스파이시"),
    WHITE_FLORAL("화이트 플로럴"),
    WOODY("우디"),
    YELLOW_FLORAL("옐로우 플로럴");

    private final String koreanName;

    Accord(String koreanName) {
        this.koreanName = koreanName;
    }

}

