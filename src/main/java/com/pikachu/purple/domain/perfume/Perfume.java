package com.pikachu.purple.domain.perfume;

import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Perfume {

    private Long id;
    private String name;
    private String imageUrl;
    private double averageScore;
    private Brand brand;

    @Setter
    private List<PerfumeAccord> accords;

    public Perfume(
        Long id,
        String name,
        String imageUrl,
        double averageScore,
        Brand brand
    ) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.averageScore = averageScore;
        this.brand = brand;
    }


}
