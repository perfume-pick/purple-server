package com.pikachu.purple.application.favorite.port.in;

import java.util.List;

public interface FavoriteCreateUseCase {

    void invoke(List<Long> perfumeIds);

}
