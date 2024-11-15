package com.pikachu.purple.domain.user.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

public record SocialLogoutId(
    @JsonProperty("id") Long userId
) {

}
