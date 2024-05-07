package com.pikachu.purple.common;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class IdGenerator implements IdentifierGenerator {

    @Override
    public Long generate(
        SharedSessionContractImplementor sharedSessionContractImplementor,
        Object o
    ) {
        return Long.parseLong(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSSS")));
    }

}
