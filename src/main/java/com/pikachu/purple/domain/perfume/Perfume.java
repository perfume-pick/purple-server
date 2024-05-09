package com.pikachu.purple.domain.perfume;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Perfume {

    private Long perfumeId;
    private String perfumeName;
    private String brandName;

    @Builder
    public Perfume(Long perfumeId, String perfumeName, String brandName){
        this.perfumeId = perfumeId;
        this.perfumeName = perfumeName;
        this.brandName = brandName;
    }

}
