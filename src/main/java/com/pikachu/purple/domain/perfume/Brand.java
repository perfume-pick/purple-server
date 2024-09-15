package com.pikachu.purple.domain.perfume;

import java.util.Set;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Brand {

    private Long id;
    private String name;
    private String imageUrl;
    private Set<Perfume> perfumes;
    private int order;

}
