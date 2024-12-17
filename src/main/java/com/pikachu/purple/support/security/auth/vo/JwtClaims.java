package com.pikachu.purple.support.security.auth.vo;

import com.auth0.jwt.interfaces.DecodedJWT;
import java.lang.reflect.Field;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Getter;

@Getter
public class JwtClaims {

    public static final Long DEFAULT_EXPIRATION_TIME_SEC = 60 * 60L;

    private RegisteredClaims registeredClaims;
    private Map<String, Object> customClaims;

    public JwtClaims(RegisteredClaims registeredClaims, Map<String, Object> customClaims) {
        this.registeredClaims = registeredClaims;
        this.customClaims = customClaims;
    }

    public static JwtClaims from(DecodedJWT claims) {
        RegisteredClaims registeredClaims = new RegisteredClaims(
            claims.getSubject(),
            claims.getExpiresAt(),
            claims.getIssuedAt(),
            claims.getNotBefore(),
            claims.getIssuer(),
            claims.getAudience(),
            claims.getId()
        );

        Map<String, Object> customClaims = new HashMap<>();
        for (Map.Entry<String, ?> entry : claims.getClaims().entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();

            if (value.toString().startsWith("\"")) {
                String valueText = value.toString();
                value = (Object) valueText.substring(1, valueText.length() - 1);
            }

            boolean isRegisteredClaim = false;
            for (Field field : RegisteredClaims.class.getDeclaredFields()) {
                if (field.getName().equals(key)) {
                    isRegisteredClaim = true;
                    break;
                }
            }

            if (!isRegisteredClaim) {
                customClaims.put(key, (Object) value);
            }
        }

        return new JwtClaims(registeredClaims, customClaims);
    }

    @Getter
    public static class RegisteredClaims {
        String sub;
        Date exp;
        Date iat;
        Date nbf;
        String iss;
        List<String> aud;
        String jti;

        public RegisteredClaims(String sub, Date exp, Date iat, Date nbf, String iss, List<String> aud, String jti) {
            this.sub = sub;
            this.exp = Date.from(Instant.now().plusSeconds(DEFAULT_EXPIRATION_TIME_SEC));
            this.iat = iat;
            this.nbf = nbf;
            this.iss = iss;
            this.aud = aud;
            this.jti = jti;
        }
    }

}


