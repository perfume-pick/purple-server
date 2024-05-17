package com.pikachu.purple.bootstrap.common.security;

import com.pikachu.purple.application.user.service.util.UserTokenService;
import com.pikachu.purple.application.user.vo.tokens.AccessToken;
import com.pikachu.purple.application.user.vo.tokens.JwtToken;
import com.pikachu.purple.bootstrap.common.vo.UserAuthentication;
import com.pikachu.purple.support.security.auth.SecurityContext;
import com.pikachu.purple.support.security.auth.SecurityContextHolder;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final String TOKEN_HEADER = "Authorization";
    private static final String TOKEN_PREFIX = "Bearer";

    private final UserTokenService userTokenService;

    @Override
    protected void doFilterInternal(
        HttpServletRequest request,
        HttpServletResponse response,
        FilterChain filterChain
    ) throws ServletException, IOException {
        String token = extractToken(request);

        if (token != null) {
            processToken(token);
        }

        filterChain.doFilter(
            request,
            response
        );

        SecurityContextHolder.clearContext();
    }

    private String extractToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(TOKEN_HEADER);

        if (bearerToken != null && bearerToken.startsWith(TOKEN_PREFIX)) {
            return bearerToken.substring(TOKEN_PREFIX.length() + 1);
        }

        return null;
    }

    private void processToken(String token) {
        JwtToken jwtToken = userTokenService.resolveJwtToken(token);
        AccessToken accessToken = userTokenService.resolveAccessToken(jwtToken.getAccessToken().substring(1, jwtToken.getAccessToken().length() - 1));
        UserAuthentication userAuthentication = UserAuthentication.from(accessToken);

        SecurityContext<UserAuthentication> securityContext = new UserSecurityContext(userAuthentication);

        SecurityContextHolder.setContext(securityContext);
    }

}
