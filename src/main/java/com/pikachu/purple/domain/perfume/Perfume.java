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
    private String imageUrl;

    @Builder
    public Perfume(
        Long perfumeId,
        String perfumeName,
        String brandName,
        String imageUrl
    ) {
        this.perfumeId = perfumeId;
        this.perfumeName = perfumeName;
        this.brandName = brandName;
        this.imageUrl = imageUrl;
    }

    public static Perfume of(
        Long perfumeId,
        String perfumeName,
        String brandName,
        String imageUrl
    ) {
        return new Perfume(
            perfumeId,
            perfumeName,
            brandName,
            imageUrl
        );
    }

}
