package com.pikachu.purple.infrastructure.user.entity;

import com.pikachu.purple.domain.user.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserJpaEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nickName;
    @Column(nullable = false)
    private String email;

    @Builder
    public UserJpaEntity(Long id, String nickName, String email){
        this.id = id;
        this.nickName = nickName;
        this.email = email;
    }

    public static UserJpaEntity toJpaEntity(User user){
        return UserJpaEntity.builder()
            .id(user.getId())
            .nickName(user.getNickName())
            .email(user.getEmail())
            .build();
    }

}
