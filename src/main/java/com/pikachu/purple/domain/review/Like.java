package com.pikachu.purple.domain.review;

import com.pikachu.purple.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Like {

    private User user;

    private Review review;

}
