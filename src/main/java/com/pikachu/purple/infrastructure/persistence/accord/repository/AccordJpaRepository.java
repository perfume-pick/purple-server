package com.pikachu.purple.infrastructure.persistence.accord.repository;

import com.pikachu.purple.infrastructure.persistence.accord.entity.AccordJpaEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccordJpaRepository extends JpaRepository<AccordJpaEntity, String> {

  Optional<AccordJpaEntity> findByName(String name);

}