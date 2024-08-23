package com.pikachu.purple.domain.perfume;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PerfumeBrand {

    private String brandName;
    private String imageUrl;

    @Builder
    public PerfumeBrand(
        String brandName,
        String imageUrl
    ){
        this.brandName = brandName;
        this.imageUrl = imageUrl;
    }

}
