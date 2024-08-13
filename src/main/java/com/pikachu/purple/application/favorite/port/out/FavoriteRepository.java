package com.pikachu.purple.application.favorite.port.out;

import com.pikachu.purple.domain.favorite.Favorite;
import java.util.List;

public interface FavoriteRepository {

    void create(List<Favorite> favorites);

}
