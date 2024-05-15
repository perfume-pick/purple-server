package com.pikachu.purple.support.security.auth;

public interface SecurityContextHolder {

    ThreadLocal<SecurityContext<?>> contextHolder = new ThreadLocal<>();

    @SuppressWarnings("unchecked")
    static <T extends Authentication> SecurityContext<T> getContext() {
        return (SecurityContext<T>) contextHolder.get();
    }

    static <T extends Authentication> void setContext(SecurityContext<T> context) {
        contextHolder.set(context);
    }

    static void clearContext() {
        contextHolder.remove();
    }

}
