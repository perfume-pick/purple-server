package com.pikachu.purple.domain.perfume;

import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Perfume {

    private Long id;
    private String name;
    private String imageUrl;
    private double averageScore;
    private Brand brand;
    private List<PerfumeAccord> accords;

}
