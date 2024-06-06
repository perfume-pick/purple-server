package com.pikachu.purple.domain.user.enums;

public enum SocialLoginProvider {
    KAKAO("kakao");

    private final String provider;

    SocialLoginProvider(String provider) {
        this.provider = provider;
    }

    public static SocialLoginProvider of(String provider) {
        for (SocialLoginProvider socialLoginProvider : values()) {
            if (checkProvider(socialLoginProvider, provider)) {
                return socialLoginProvider;
            }
        }

        throw new IllegalArgumentException("지원하지 않는 형식의 소셜 로그인 provider입니다.");
    }

    private static boolean checkProvider(SocialLoginProvider socialLoginProvider, String provider) {
        return socialLoginProvider.provider.equals(provider);
    }


}
