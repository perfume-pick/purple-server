package com.pikachu.purple.infrastructure.persistence.perfume.adapter;

import com.pikachu.purple.domain.perfume.Perfume;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import static org.jooq.generated.purple.Tables.PERFUME;

@Repository
@RequiredArgsConstructor
class PerfumeJooqAdaptor {
    private final DSLContext dsl;

    public Perfume findByPerfumeId(Long perfumeId) {
        return dsl.selectFrom(PERFUME)
                .where(PERFUME.PERFUME_ID.eq(perfumeId))
                .fetchOneInto(Perfume.class);
    }
}
