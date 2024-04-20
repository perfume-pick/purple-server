package com.pikachu.purple.bootstrap.auth.dto.request;

import com.pikachu.purple.domain.user.enums.SocialLoginProvider;

public record SocialLoginTryRequest(SocialLoginProvider socialLoginProvider) {
}
