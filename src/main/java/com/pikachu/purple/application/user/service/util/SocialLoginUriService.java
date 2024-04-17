package com.pikachu.purple.application.user.service.util;

import com.pikachu.purple.domain.user.enums.SocialLoginProvider;
import java.net.URI;

public interface SocialLoginUriService {

    URI createUri(SocialLoginProvider socialLoginProvider);

}
