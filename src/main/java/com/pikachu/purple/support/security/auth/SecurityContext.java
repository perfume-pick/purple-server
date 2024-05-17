package com.pikachu.purple.support.security.auth;

public interface SecurityContext<T extends Authentication> {

    T getAuthentication();

    void setAuthentication(T authentication);

}
