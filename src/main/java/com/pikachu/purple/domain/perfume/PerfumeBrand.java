package com.pikachu.purple.domain.perfume;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PerfumeBrand {

    private String brandName;

    @Builder
    public PerfumeBrand(String brandName){
        this.brandName = brandName;
    }

}
