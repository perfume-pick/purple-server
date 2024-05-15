package com.pikachu.purple.bootstrap.common.security;

import com.pikachu.purple.bootstrap.common.exception.BusinessException;
import com.pikachu.purple.bootstrap.common.exception.ErrorCode;
import com.pikachu.purple.support.security.auth.Authentication;
import com.pikachu.purple.support.security.auth.SecurityContext;
import com.pikachu.purple.support.security.auth.SecurityContextHolder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthorizationInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(
        HttpServletRequest request,
        HttpServletResponse response,
        Object handler
    ) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return HandlerInterceptor.super.preHandle(request, response, handler);
        }

        if (hasSecuredAnnotation(handler) && isNotAuthenticated()) {
            throw new BusinessException(ErrorCode.UNAUTHORIZED_REQUEST);
        }

        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    private boolean hasSecuredAnnotation(Object handler) {
        if (handler instanceof HandlerMethod handlerMethod) {
            return handlerMethod.hasMethodAnnotation(Secured.class);
        }

        return false;
    }

    private boolean isNotAuthenticated() {
        SecurityContext<Authentication> userAuthentication = SecurityContextHolder.getContext();

        return userAuthentication == null;
    }

}
