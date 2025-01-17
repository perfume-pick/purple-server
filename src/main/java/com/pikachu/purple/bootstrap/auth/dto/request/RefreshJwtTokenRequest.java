package com.pikachu.purple.bootstrap.auth.dto.request;

import jakarta.validation.constraints.NotBlank;

public record RefreshJwtTokenRequest(@NotBlank String jwtToken) {}
